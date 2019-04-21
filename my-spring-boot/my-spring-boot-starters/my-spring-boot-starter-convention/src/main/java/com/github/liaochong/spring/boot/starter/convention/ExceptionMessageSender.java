package com.github.liaochong.spring.boot.starter.convention;

import java.lang.reflect.Method;

/**
 * 异常信息发送
 *
 * @author liaochong
 * @version 1.0
 */
public interface ExceptionMessageSender {

    /**
     * 异常信息发送
     *
     * @param sendUrl   url
     * @param method    方法
     * @param args      参数
     * @param throwable 异常
     */
    void sendMessage(String sendUrl, Method method, Object[] args, Throwable throwable);
}
