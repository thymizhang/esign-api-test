package com.esign.pojo;

import lombok.Data;

import java.util.Map;

/**
 * e签宝请求返回结果
 *
 * @Author thymi
 * @Date 2020/12/30
 */
@Data
public class EsignResult {

    /**
     * 返回码
     */
    int code;

    /**
     * 消息
     */
    String message;

    /**
     * 返回数据
     */
    String data;
}
