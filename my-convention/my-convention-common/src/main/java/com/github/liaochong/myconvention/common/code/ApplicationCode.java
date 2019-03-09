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

import com.github.liaochong.myconvention.common.exception.ServiceException;

/**
 * 系统编码，含成功、错误、异常编码
 *
 * @author liaochong
 * @version 1.0
 */
public interface ApplicationCode {

    /**
     * 编码
     *
     * @return 编码
     */
    String code();

    /**
     * 编码对应的信息
     *
     * @return 信息
     */
    String message();

    default void failure() {
        throw new ServiceException(this);
    }
}
