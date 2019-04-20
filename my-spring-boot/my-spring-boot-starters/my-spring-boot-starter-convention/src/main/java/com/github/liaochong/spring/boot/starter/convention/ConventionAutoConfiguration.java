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

import com.github.liaochong.spring.boot.starter.convention.properties.ConventionProperties;
import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;

/**
 * @author liaochong
 * @version 1.0
 */
@EnableConfigurationProperties(ConventionProperties.class)
public class ConventionAutoConfiguration {

    @Resource
    private ConventionProperties conventionProperties;

    @Bean
    @ConditionalOnMissingBean
    public GlobalResultExceptionHandler globalResultExceptionHandler() {
        return new GlobalResultExceptionHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public GlobalParametersValidator globalParametersValidator() {
        boolean fastFail = conventionProperties.isValidMethodParamsFastFail();
        ValidatorFactory factory = Validation.byProvider(HibernateValidator.class).configure().failFast(fastFail).buildValidatorFactory();
        ExecutableValidator executableValidator = factory.getValidator().forExecutables();
        return new GlobalParametersValidator(executableValidator);
    }
}
