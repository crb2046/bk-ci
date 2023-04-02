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

package com.tencent.devops.dispatch.docker.common

import com.tencent.devops.common.api.annotation.BkFieldI18n
import com.tencent.devops.common.api.enums.I18nTranslateTypeEnum
import com.tencent.devops.common.api.pojo.ErrorType

@Suppress("ALL")
enum class ErrorCodeEnum(
    @BkFieldI18n
    val errorType: ErrorType,
    val errorCode: Int,
    @BkFieldI18n(translateType = I18nTranslateTypeEnum.VALUE, reusePrefixFlag = false)
    val formatErrorMessage: String
) {
    SYSTEM_ERROR(ErrorType.SYSTEM, 2128001, "2128001"),// Dispatcher-docker系统错误
    NO_IDLE_VM_ERROR(ErrorType.SYSTEM, 2128002, "2128002"),// 构建机启动失败，没有空闲的构建机
    POOL_VM_ERROR(ErrorType.SYSTEM, 2128003, "2128003"),// 容器并发池分配异常
    NO_SPECIAL_VM_ERROR(ErrorType.SYSTEM, 2128004, "2128004"),// Start build Docker VM failed, no available Docker VM in specialIpList
    NO_AVAILABLE_VM_ERROR(ErrorType.SYSTEM, 2128005, "2128005"),// Start build Docker VM failed, no available Docker VM. Please wait a moment and try again.
    DOCKER_IP_NOT_AVAILABLE(ErrorType.SYSTEM, 2128006, "2128006"),// Docker ip is not available.
    END_VM_ERROR(ErrorType.SYSTEM, 2128007, "2128007"),// End build Docker VM failed
    START_VM_FAIL(ErrorType.SYSTEM, 2128008, "2128008"),// Start build Docker VM failed
    RETRY_START_VM_FAIL(ErrorType.USER, 2128009, "2128009"),// Start build Docker VM failed, retry times.
    GET_VM_STATUS_FAIL(ErrorType.SYSTEM, 2128010, "2128010"),// Get container status failed
    GET_CREDENTIAL_FAIL(ErrorType.USER, 2128011, "2128011"),// Get credential failed
    IMAGE_ILLEGAL_EXCEPTION(ErrorType.USER, 2128012, "2128012"),// User Image illegal, not found or credential error
    IMAGE_CHECK_LEGITIMATE_OR_RETRY(ErrorType.USER, 2128013, "2128013"),// 登录调试失败,请检查镜像是否合法或重试。
    DEBUG_CONTAINER_SHUTS_DOWN_ABNORMALLY(ErrorType.SYSTEM, 2128014, "2128014")// 登录调试失败，调试容器异常关闭，请重试。
}
