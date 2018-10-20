package com.fido.fcnetworksite.exception

import com.fido.fcnetworksite.base.DataMap
import com.fido.fcnetworksite.enum.StatusEnum
import com.fido.fcnetworksite.util.ResponseBuilder

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 19:38 2018/10/18
 */
open class BaseException(statusCode: StatusEnum, message: String?) : RuntimeException(message) {
    val code: Int = statusCode.code

    constructor(statusCode: StatusEnum) : this(statusCode, statusCode.message)

    open fun toResponse(): DataMap {
        return toResponseBuilder().build()
    }

    open fun toResponse(trace: String): DataMap {
        return toResponseBuilder().trace(trace).build()
    }

    private fun toResponseBuilder(): ResponseBuilder {
        return ResponseBuilder().error(code).message(msg())
    }

    fun msg(): String {
        return message ?: ""
    }
}