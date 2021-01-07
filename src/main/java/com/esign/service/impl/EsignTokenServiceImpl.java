package com.esign.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.esign.service.EsignTokenService;
import com.esign.util.EsignOpenSource;
import com.esign.util.EsignUtil;
import com.esign.util.RedisUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author thymi
 * @Date 2020/12/30
 */
@Service
public class EsignTokenServiceImpl implements EsignTokenService {

    final
    RedisUtil redisUtil;

    final
    RedisTemplate redisTemplate;

    public EsignTokenServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.redisUtil = new RedisUtil(redisTemplate);
    }

    @Override
    public String getToken() {
        /* 从缓存获取token */
        Object cacheToken = redisUtil.get(EsignUtil.ESIGN_CACHE_TOKEN_KEY);
        if(cacheToken == null){
            /* 请求参数 */
            Map<String, Object> paramMap = new HashMap<>(1);
            paramMap.put("appId", EsignOpenSource.ESIGN_APP_ID);
            paramMap.put("secret", EsignOpenSource.ESIGN_APP_SECRET);
            paramMap.put("grantType", EsignOpenSource.ESIGN_APP_GRANT_TYPE);
            /* 请求地址 */
            String url = EsignUtil.getHost() + EsignOpenSource.ESIGN_OAUTH2_ACCESS_TOKEN;
            String returnResult = HttpUtil.get(url, paramMap);

            String data = EsignUtil.getResultData(returnResult);
            Map<String, String> map = JSONUtil.toBean(data, Map.class);
            String token = map.get("token");
            /* 将token缓存 */
            redisUtil.set(EsignUtil.ESIGN_CACHE_TOKEN_KEY, token);
            /* e签名的token有效期是120分钟，可能会减少时间，这里我们设置为110分钟 */
            redisUtil.expire(EsignUtil.ESIGN_CACHE_TOKEN_KEY, 6600);
            System.out.println("========从接口获取的token");
            System.out.println(token);
            return token;
        }
        System.out.println("========从缓存获取的token");
        System.out.println(cacheToken);
        return cacheToken.toString();
    }
}
