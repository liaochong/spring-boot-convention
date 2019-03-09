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
package com.github.liaochong.myconvention.common.util;

import com.github.liaochong.myconvention.api.Result;
import com.github.liaochong.myconvention.common.code.DefaultApplicationCode;
import lombok.experimental.UtilityClass;

/**
 * Results用来返回最终对象，只提供成功方法，失败异常均以异常形式抛出，由my-spring-boot-starter-convention统一异常拦截
 *
 * @author liaochong
 * @version 1.0
 */
@UtilityClass
public class Results {

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(DefaultApplicationCode.REQUEST_SUCCESS.code(), DefaultApplicationCode.REQUEST_SUCCESS.message(), data);
    }
}
