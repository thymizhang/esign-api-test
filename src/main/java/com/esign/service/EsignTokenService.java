package com.esign.service;

/**
 * 鉴权令牌服务
 *
 * @Author thymi
 * @Date 2020/12/30
 */
public interface EsignTokenService {

    /**
     * 获取鉴权令牌
     *
     * @return 鉴权令牌
     */
    String getToken();
}
