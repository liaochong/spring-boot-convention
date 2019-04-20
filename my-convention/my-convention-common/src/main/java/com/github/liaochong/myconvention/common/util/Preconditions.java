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

import com.github.liaochong.myconvention.common.code.ApplicationCode;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;

/**
 * 预校验工具
 *
 * @author liaochong
 * @version 1.0
 */
@UtilityClass
public class Preconditions {

    public static void checkArgument(boolean expression, ApplicationCode applicationCode) {
        if (!expression) {
            applicationCode.failure();
        }
    }

    public static void checkState(boolean expression, ApplicationCode applicationCode) {
        if (!expression) {
            applicationCode.failure();
        }
    }

    public static <T> T checkNotNull(T reference, ApplicationCode applicationCode) {
        if (reference == null) {
            applicationCode.failure();
        }
        return reference;
    }

    public static <T> void checkNull(T reference, ApplicationCode applicationCode) {
        if (Objects.nonNull(reference)) {
            applicationCode.failure();
        }
    }

    public static <T> void checkNotEmpty(List<T> list, ApplicationCode applicationCode) {
        if (Objects.isNull(list) || list.isEmpty()) {
            applicationCode.failure();
        }
    }

    public static <T> void checkEmpty(List<T> list, ApplicationCode applicationCode) {
        if (Objects.nonNull(list) && !list.isEmpty()) {
            applicationCode.failure();
        }
    }

    public static <T> void checkNotBlank(String s, ApplicationCode applicationCode) {
        if (Objects.isNull(s) || s.trim().isEmpty()) {
            applicationCode.failure();
        }
    }

    public static <T> void checkBlank(String s, ApplicationCode applicationCode) {
        if (Objects.nonNull(s) && !s.trim().isEmpty()) {
            applicationCode.failure();
        }
    }

}
