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
package com.github.liaochong.myexample.service;

import com.github.liaochong.myexample.model.TestRequest;
import com.github.liaochong.spring.boot.starter.convention.annotation.Validated;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

/**
 * @author liaochong
 * @version 1.0
 */
@Service
public class TestService {

    @Validated
    public String test(@Valid TestRequest request) {
        return request.getName();
    }
}
