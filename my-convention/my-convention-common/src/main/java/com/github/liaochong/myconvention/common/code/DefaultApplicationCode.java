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
package com.github.liaochong.myconvention.common.code;

/**
 * 默认应用编码，框架使用，其他请勿使用
 *
 * @author liaochong
 * @version 1.0
 */
public enum DefaultApplicationCode implements ApplicationCode {
    /**
     * 请求成功
     */
    REQUEST_SUCCESS("C_0", "请求成功"),
    /**
     * 系统异常
     */
    SYSTEM_EXCEPTION("C_1", "系统异常"),
    /**
     * 参数校验失败
     */
    VALIDATE_FAILURE("C_2", "参数校验失败"),
    /**
     * 参数绑定非法
     */
    BINDING_INVALID_FAILURE("C_2_1", "参数非法，请检查参数正确性");

    private String code;

    private String message;

    DefaultApplicationCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
