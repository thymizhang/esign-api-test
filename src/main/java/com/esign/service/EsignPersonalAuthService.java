package com.esign.service;

/**
 * e签宝个人认证服务
 *
 * @Author thymi
 * @Date 2020/12/30
 */
public interface EsignPersonalAuthService {

    /**
     * 创建个人账号
     *
     * @param phone 手机号码，同时作为第三方账号
     * @param name  账号姓名，最好是真实姓名，后面认证会用到
     * @return e签宝个人账号
     */
    String createAccount(String phone, String name);

    /**
     * 获取e签宝个人账号
     *
     * @param phone 手机号码
     * @return e签宝个人账号
     */
    String getAccountId(String phone);

    /**
     * 运营商三要素实名认证
     *
     * @param phone 手机号码
     * @return 认证信息
     */
    String telecom3Factors(String phone);

}
