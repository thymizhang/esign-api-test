package com.esign.service.impl;

import cn.hutool.http.HttpRequest;
import com.esign.service.EsignPersonalAuthService;
import com.esign.service.EsignTokenService;
import com.esign.util.EsignOpenSource;
import com.esign.util.EsignUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * e签宝个人认证服务实现类
 *
 * @Author thymi
 * @Date 2020/12/30
 */
@Service
public class EsignPersonalAuthServiceImpl implements EsignPersonalAuthService {

    final
    EsignTokenService esignTokenService;

    public EsignPersonalAuthServiceImpl(EsignTokenService esignTokenService) {
        this.esignTokenService = esignTokenService;
    }

    @Override
    public String createAccount(String phone, String name) {
        return null;
    }

    @Override
    public String getAccountId(String phone) {
        /* 请求参数 */
        Map<String, Object> paramMap = new HashMap<>(1);
        paramMap.put("thirdPartyUserId", phone);
        /* 请求地址 */
        String url = EsignUtil.getHost() + EsignOpenSource.ESIGN_AUTH_PERSONAL_GET_ACCOUNT;

        String token = esignTokenService.getToken();

        HttpRequest httpRequest = EsignUtil.getGetHttpRequest(url, token);
        String body = httpRequest.form(paramMap).execute().body();
        return body;
    }

    @Override
    public String telecom3Factors(String phone) {
        return null;
    }


}
