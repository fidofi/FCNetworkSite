package com.fido.fcnetworksite.exception

import com.fido.fcnetworksite.base.DataMap
import com.fido.fcnetworksite.enum.StatusEnum
import com.fido.fcnetworksite.util.ResponseBuilder

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/20 19:37
 * @description:
 */
class UserException : BaseException(statusCode, message) {
    val statusCode: StatusEnum =
    val message: String

    constructor(statusCode: StatusEnum, message: String?, statusCode1: StatusEnum, message1: String) : super(statusCode, message) {
        this.statusCode = statusCode1
        this.message = message1
    }

    constructor(statusCode: StatusEnum, statusCode1: StatusEnum, message: String) : super(statusCode) {
        this.statusCode = statusCode1
        this.message = message
    }

    override fun toResponse(): DataMap {
        return ResponseBuilder().error(code).message(message).build()
    }
}