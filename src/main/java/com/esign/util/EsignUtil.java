package com.esign.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.esign.exception.EsignTokenFailureException;
import com.esign.pojo.EsignResult;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author thymi
 * @Date 2020/12/30
 */
public class EsignUtil {

    /**
     * 配置文件名，默认保存位置：classes：/
     */
    private static final String ESIGN_CONFIG_FILE = "esign.properties";

    /**
     * 错误码信息
     */
    private static final Properties PROPERTIES = EsignUtil.getProperties();

    /**
     * token存放在缓存中的key
     */
    public static final String ESIGN_CACHE_TOKEN_KEY = "ESIGN_TOKEN";

    /**
     * 从文件读取错误码信息
     *
     * @return Properties
     */
    private static Properties getProperties() {
        Properties properties = null;
        try {
            properties = PropertiesLoaderUtils.loadAllProperties(EsignUtil.ESIGN_CONFIG_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * 获取当前接口Host
     *
     * @return 接口Host
     */
    public static String getHost() {
        String test = "test";
        String prod = "prod";
        String profile = EsignUtil.PROPERTIES.getProperty("esign.profile");
        if (prod.equals(profile)) {
            return EsignOpenSource.ESIGN_HOST_PROD;
        }

        if (test.equals(profile)) {
            return EsignOpenSource.ESIGN_HOST_TEST;
        }

        return null;
    }

    /**
     * 获取返回结果中的Code
     *
     * @param result 请求返回结果
     * @return Code
     */
    public static int getResultCode(String result) {
        EsignResult esignResult = JSONUtil.toBean(result, EsignResult.class);
        return esignResult.getCode();
    }

    /**
     * 获取返回结果中的Data
     * 参考：https://open.esign.cn/doc/detail?namespace=opendoc%2Fpaas_api&id=opendoc%2Fpaas_api%2Fwukong&searchText=
     *
     * @param result 请求返回结果
     * @return Data
     */
    public static String getResultData(String result) {
        EsignResult esignResult = JSONUtil.toBean(result, EsignResult.class);
        int tokenError = 401;
        /* 0表示请求成功 */
        if (esignResult.getCode() == 0) {
            return esignResult.getData();
        }
        if (esignResult.getCode() == tokenError) {
            throw new EsignTokenFailureException();
        }
        return "";
    }

    /**
     * 获取封装了头信息的请求
     *
     * @param url
     * @return
     */
    public static HttpRequest getGetHttpRequest(String url, String token) {
        Map<String, String> headers = new HashMap<>(2);
        headers.put(EsignOpenSource.ESIGN_HTTP_HEAD_APP_ID, EsignOpenSource.ESIGN_APP_ID);
        headers.put(EsignOpenSource.ESIGN_HTTP_HEAD_TOKEN, token);
        return HttpRequest.get(url).headerMap(headers, true);
    }
}
