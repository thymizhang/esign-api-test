package com.esign.exception;

/**
 * Token失效异常
 *
 * @Author thymi
 * @Date 2020/12/30
 */
public class EsignTokenFailureException extends RuntimeException {
    public EsignTokenFailureException() {
        super("Token已失效");
    }
}
