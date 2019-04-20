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

import com.github.liaochong.myconvention.common.code.DefaultApplicationCode;
import com.github.liaochong.myconvention.common.exception.ServiceException;
import com.github.liaochong.myconvention.common.exception.ServiceInvalidException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.transform.Result;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 控制层增强
 *
 * @author liaochong
 * @version 1.0
 */
@RestControllerAdvice
public class ControllerAdvice {

    private static final String TYPE_ERROR_MESSAGE = "Failed to convertproperty value of type";

    @ExceptionHandler(value = BindException.class)
    public Result bindExceptionHandler(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> errors = bindingResult.getAllErrors();
        List<String> errorMessages = errors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        boolean isTypeError = errorMessages.stream().anyMatch(m -> m.startsWith(TYPE_ERROR_MESSAGE));
        if (isTypeError) {
            throw new ServiceException(DefaultApplicationCode.BINDING_INVALID);
        }
        throw new ServiceInvalidException(errorMessages);
    }

}
