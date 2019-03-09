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
package com.github.liaochong.myconvention.api;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * @author liaochong
 * @version 1.0
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Paging implements Serializable {
    private static final long serialVersionUID = 3412497962847823219L;

    /**
     * 默认pageIndex
     */
    public static final int DEFAULT_PAGE_INDEX = 1;
    /**
     * 默认pageSize，视情况调整
     */
    public static final int DEFAULT_PAGE_SIZE = 20;
    /**
     * 最大pageSize，视情况调整
     */
    public static final int MAX_PAGE_SIZE = 200;

    int pageIndex = DEFAULT_PAGE_INDEX;

    int pageSize = DEFAULT_PAGE_SIZE;

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex > 0 ? pageIndex : DEFAULT_PAGE_INDEX;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > MAX_PAGE_SIZE) {
            pageSize = MAX_PAGE_SIZE;
        }
        this.pageSize = pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE;
    }

    /**
     * 偏移量
     *
     * @return 偏移量
     */
    public long getOffset() {
        return (pageIndex - 1) * pageSize;
    }
}
