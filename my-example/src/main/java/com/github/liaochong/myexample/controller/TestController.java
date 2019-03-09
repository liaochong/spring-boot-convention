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
package com.github.liaochong.myexample.controller;

import com.github.liaochong.myconvention.api.Result;
import com.github.liaochong.myconvention.common.util.Results;
import com.github.liaochong.myexample.model.TestRequest;
import com.github.liaochong.myexample.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liaochong
 * @version 1.0
 */
@RestController
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("/test")
    public Result<String> test(TestRequest request) {
        String name = testService.test(request);
        return Results.success(name);
    }
}
