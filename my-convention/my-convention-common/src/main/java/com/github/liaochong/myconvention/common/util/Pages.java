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

import com.github.liaochong.myconvention.api.PagingResult;
import com.sun.istack.internal.NotNull;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author liaochong
 * @version 1.0
 */
@UtilityClass
public class Pages {

    public static <E, T> PagingResult<T> map(@NotNull PagingResult<E> originPagingResult, @NotNull Function<E, T> convertFunction) {
        PagingResult<T> result = new PagingResult<>();
        result.setPageIndex(originPagingResult.getPageIndex());
        result.setPageSize(originPagingResult.getPageSize());
        result.setTotalCount(originPagingResult.getTotalCount());

        List<T> convertResult = originPagingResult.getData().stream().map(convertFunction).collect(Collectors.toList());
        result.setData(convertResult);
        return result;
    }

}
