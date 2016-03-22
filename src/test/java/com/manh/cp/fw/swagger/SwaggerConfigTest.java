/*
 * Copyright (c) 2016 Manhattan Associates, Inc.  All Rights Reserved.
 *
 * Confidential, Proprietary and Trade Secrets Notice
 *
 * Use of this software is governed by a license agreement. This software
 * contains confidential, proprietary and trade secret information of
 * Manhattan Associates, Inc. and is protected under United States and
 * international copyright and other intellectual property laws. Use, disclosure,
 * reproduction, modification, distribution, or storage in a retrieval system in
 * any form or by any means is prohibited without the prior express written
 * permission of Manhattan Associates, Inc.
 *
 * Manhattan Associates, Inc.
 * 2300 Windy Ridge Parkway, 10th Floor
 * Atlanta, GA 30339 USA
 *
 */

package com.manh.cp.fw.swagger;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.spring.web.plugins.Docket;

/**
 * Created by prajan on 3/21/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SwaggerConfigTest.Application.class)
@WebIntegrationTest(randomPort = true)
public class SwaggerConfigTest
{

    @Value("${local.server.port}")
    private int localPort;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void swagger_bean_created()
    {
        assertThat(applicationContext.getBean(Docket.class), notNullValue());
    }

    @Test
    public void simple_controller()
    {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> jsonResponseEntity = restTemplate
                .getForEntity("http://localhost:"+localPort+"/v2/api-docs", String.class);

        assertThat(jsonResponseEntity.getStatusCode(), equalTo(HttpStatus.OK));


        String json = jsonResponseEntity.getBody();

        assertThat(json, isJson());
        assertThat(json, hasJsonPath("$.paths.*", hasSize(1)));

    }

    @SpringBootApplication
    @RestController
    protected static class Application
    {
        @RestController
        static class Controller {

            @RequestMapping(value = "/api/hello", method = RequestMethod.GET)
            public String hello()
            {
                return "hello";
            }
        }


    }

}