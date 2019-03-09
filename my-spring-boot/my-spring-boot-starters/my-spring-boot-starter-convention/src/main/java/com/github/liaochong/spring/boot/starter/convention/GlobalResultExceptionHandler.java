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
import com.github.liaochong.myconvention.common.exception.ServiceErrorException;
import com.github.liaochong.myconvention.common.exception.ServiceException;
import com.github.liaochong.myconvention.common.exception.ServiceInvalidException;
import com.github.liaochong.myconvention.common.util.UnsafeResults;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

/**
 * @author liaochong
 * @version 1.0
 */
@Aspect
@Order(-999999)
@Slf4j
public class GlobalResultExceptionHandler {

    @Pointcut("execution(com.github.liaochong.myconvention.api.Result+ *..*.*(..))")
    private void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (ServiceInvalidException e) {
            return UnsafeResults.failure(DefaultApplicationCode.VALIDATE_FAILURE, e.getViolationItems());
        } catch (ServiceException e) {
            return UnsafeResults.failure(e.getApplicationCode());
        } catch (ServiceErrorException e) {
            log.error("系统异常", e);
            return UnsafeResults.error();
        } catch (Exception e) {
            log.error("未知的系统异常", e);
            return UnsafeResults.error();
        }
    }

}
