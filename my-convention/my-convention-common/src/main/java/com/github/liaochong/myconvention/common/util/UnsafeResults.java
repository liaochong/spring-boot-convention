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
import com.github.liaochong.myconvention.common.code.ApplicationCode;
import com.github.liaochong.myconvention.common.code.DefaultApplicationCode;
import lombok.experimental.UtilityClass;

/**
 * 框架使用，建议不要使用，框架升级不保证兼容
 *
 * @author liaochong
 * @version 1.0
 */
@UtilityClass
public class UnsafeResults {

    public static <T> Result<T> failure(ApplicationCode applicationCode) {
        return new Result<>(applicationCode.code(), applicationCode.message(), null);
    }

    public static <T> Result<T> failure(ApplicationCode applicationCode, T data) {
        return new Result<>(applicationCode.code(), applicationCode.message(), data);
    }

    public static <T> Result<T> error() {
        return new Result<>(DefaultApplicationCode.SYSTEM_EXCEPTION.code(), DefaultApplicationCode.SYSTEM_EXCEPTION.message(), null);
    }

}
