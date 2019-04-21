/*
 * Copyright 2019 liaochong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.liaochong.spring.boot.starter.convention;

import com.alibaba.fastjson.JSON;
import com.github.liaochong.spring.boot.starter.convention.properties.ConventionProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.concurrent.CompletableFuture;

/**
 * 默认异常信息发送
 *
 * @author liaochong
 * @version 1.0
 */
@Slf4j
public class DefaultExceptionMessageSender implements ExceptionMessageSender {

    private ConventionProperties conventionProperties;

    private String applicationName;

    public DefaultExceptionMessageSender(ConventionProperties conventionProperties, String applicationName) {
        this.conventionProperties = conventionProperties;
        this.applicationName = applicationName;
    }

    @Override
    public void sendMessage(String sendUrl, Method method, Object[] args, Throwable throwable) {
        CompletableFuture.runAsync(() -> {
            try {
                Message message = new Message();
                MarkDown markDown = new MarkDown();
                markDown.setTitle("异常信息");
                markDown.setTitle("**应用名称：**" + applicationName + " \n\n "
                        + "**所属环境：**" + conventionProperties.getEnvName() + " \n\n "
                        + "**异常类：**" + throwable.getStackTrace()[0].getClassName() + " \n\n "
                        + "**异常方法：**" + method + " \n\n "
                        + "**异常参数：**" + JSON.toJSONString(args) + " \n\n "
                        + "**应用IP：**" + getInet4Address() + " \n\n "
                        + "**异常行数：**" + throwable.getStackTrace()[0].getLineNumber() + " \n\n "
                        + "**异常信息：**\n\n（" + throwable.getClass().getName() + "）" + throwable.getMessage() + " \n\n ");
                message.setMarkDown(markDown);

                String result = Request.Post(conventionProperties.getMessageSendUrl())
                        .connectTimeout(5_000)
                        .socketTimeout(3_0000)
                        .bodyString(JSON.toJSONString(message), ContentType.parse(conventionProperties.getMessageContentType()).withCharset(StandardCharsets.UTF_8))
                        .execute()
                        .returnContent().asString(StandardCharsets.UTF_8);
                if (log.isInfoEnabled()) {
                    log.info("Message send result：{}", result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    private static String getInet4Address() throws Exception {
        Enumeration<NetworkInterface> nis;
        nis = NetworkInterface.getNetworkInterfaces();
        for (; nis.hasMoreElements(); ) {
            NetworkInterface ni = nis.nextElement();
            Enumeration<InetAddress> ias = ni.getInetAddresses();
            for (; ias.hasMoreElements(); ) {
                InetAddress ia = ias.nextElement();
                //ia instanceof Inet6Address && !ia.equals("")
                if (ia instanceof Inet4Address && !ia.getHostAddress().equals("127.0.0.1")) {
                    return ia.getHostAddress();
                }
            }
        }
        return null;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    private class Message {

        String msgtype = "markdown";

        MarkDown markDown;
    }


    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    private class MarkDown {

        String title;

        String text;

    }
}
