package com.esign.util;

/**
 * e签宝接口地址
 *
 * @Author thymi
 * @Date 2020/12/30
 */
public class EsignOpenSource {

    public static final String ESIGN_APP_ID = "7438835487";
    public static final String ESIGN_APP_SECRET = "3041912f61703f584afa5768d98c66b3";
    public static final String ESIGN_APP_GRANT_TYPE = "client_credentials";

    public static final String ESIGN_HTTP_HEAD_APP_ID = "X-Tsign-Open-App-Id";
    public static final String ESIGN_HTTP_HEAD_TOKEN = "X-Tsign-Open-Token";

    /**
     * 正式环境地址
     */
    public static final String ESIGN_HOST_PROD = "https://openapi.esign.cn";

    /**
     * 沙箱环境（测试环境）地址
     */
    public static final String ESIGN_HOST_TEST = "https://smlopenapi.esign.cn";

    /* ========== 鉴权服务接口 ========== */

    /**
     * 获取鉴权Token
     */
    public static final String ESIGN_OAUTH2_ACCESS_TOKEN = "/v1/oauth2/access_token";

    /* ========== 个人认证服务接口 ========== */

    /**
     * 查询个人账号
     */
    public static final String ESIGN_AUTH_PERSONAL_GET_ACCOUNT = "/v1/accounts/getByThirdId";


}
