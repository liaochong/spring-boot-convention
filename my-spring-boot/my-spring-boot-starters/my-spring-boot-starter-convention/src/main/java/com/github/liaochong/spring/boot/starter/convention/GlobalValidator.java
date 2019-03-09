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

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.validator.HibernateValidator;
import org.springframework.core.annotation.Order;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;

/**
 * @author liaochong
 * @version 1.0
 */
@Aspect
@Order(-999998)
public class GlobalValidator {

    private static final ExecutableValidator executableValidator;

    static {
        ValidatorFactory factory = Validation.byProvider(HibernateValidator.class).configure().buildValidatorFactory();
        executableValidator = factory.getValidator().forExecutables();
    }

    @Pointcut("@annotation(com.github.liaochong.spring.boot.starter.convention.annotation.Validated)||@within(com.github.liaochong.spring.boot.starter.convention.annotation.Validated)")
    private void pointcut() {

    }

    @Before("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }
}
