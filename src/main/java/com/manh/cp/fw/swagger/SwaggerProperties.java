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

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by prajan on 3/21/16.
 */
@ConfigurationProperties(prefix = "manh.cp.swagger", ignoreUnknownFields = false)
public class SwaggerProperties
{
    private boolean enabled = true;
    private String pathsRegex = "/api.*";

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public String getPathsRegex()
    {
        return pathsRegex;
    }

    public void setPathsRegex(String pathsRegex)
    {
        this.pathsRegex = pathsRegex;
    }
}
