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

import com.github.liaochong.myconvention.common.exception.ServiceInvalidException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;

import javax.validation.ConstraintViolation;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author liaochong
 * @version 1.0
 */
@Aspect
@Order(-999998)
public class GlobalValidator {

    private ExecutableValidator executableValidator;

    public GlobalValidator(ExecutableValidator executableValidator) {
        this.executableValidator = executableValidator;
    }

    @Pointcut("@annotation(com.github.liaochong.spring.boot.starter.convention.annotation.Validated)||@within(com.github.liaochong.spring.boot.starter.convention.annotation.Validated)")
    private void pointcut() {

    }

    @Before("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        Set<ConstraintViolation<Object>> validResult = executableValidator.validateParameters(joinPoint.getTarget(), method, joinPoint.getArgs());
        if (validResult.isEmpty()) {
            return joinPoint.proceed();
        }
        List<String> invalidMessages = validResult.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        throw new ServiceInvalidException(invalidMessages);
    }
}
