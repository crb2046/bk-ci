/*
 * Tencent is pleased to support the open source community by making BK-CI 蓝鲸持续集成平台 available.
 *
 * Copyright (C) 2019 THL A29 Limited, a Tencent company.  All rights reserved.
 *
 * BK-CI 蓝鲸持续集成平台 is licensed under the MIT license.
 *
 * A copy of the MIT License is included in this file.
 *
 *
 * Terms of the MIT License:
 * ---------------------------------------------------
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of
 * the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
 * NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.tencent.devops.environment.pojo

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "环境信息-Node数量")
data class EnvWithNodeCount(
    @get:Schema(title = "环境所属项目Id", required = true)
    val projectId: String,
    @get:Schema(title = "环境 HashId", required = true)
    val envHashId: String,
    @get:Schema(title = "环境名称", required = true)
    val name: String,
    @get:Schema(title = "正常节点数量", required = true)
    val normalNodeCount: Int,
    @get:Schema(title = "异常节点数量", required = true)
    val abnormalNodeCount: Int,
    @get:Schema(title = "源项目", required = false)
    val sharedProjectId: String?,
    @get:Schema(title = "分享人", required = false)
    val sharedUserId: String?,
    @get:Schema(title = "节点hash id", required = false)
    val nodeHashIds: List<String>? = null
)
