package com.fido.fcnetworksite.util

import com.fido.fcnetworksite.base.DataMap
import com.fido.fcnetworksite.enum.StatusEnum

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/19 22:31
 * @description:
 */
class ResponseBuilder {
    private var code: Int = 0
    private var message: String? = null
    private val data = DataMap()

    fun ok(): ResponseBuilder {
        this.code = StatusEnum.SUCCESS.code
        this.message = StatusEnum.SUCCESS.message
        return this
    }

    fun code(code: Int): ResponseBuilder {
        this.code = code
        return this
    }

    fun error(code: Int): ResponseBuilder {
        this.code = code
        return this
    }

    fun error(error: StatusEnum): ResponseBuilder {
        this.code = error.code
        this.message = error.message
        return this
    }

    fun message(message: String?): ResponseBuilder {
        this.message = message ?: ""
        return this
    }

    fun data(content: Any?): ResponseBuilder {
        this.code = StatusEnum.SUCCESS.code
        this.message = StatusEnum.SUCCESS.message
        data.addAttribute(ResponseBuilder.DATA, content!!)
        return this
    }


    fun build(): DataMap {
        val dataMap = DataMap()
        dataMap.addAttribute(ResponseBuilder.STATUS_CODE, code)
        dataMap.putAll(data)
        if (message != null) {
            dataMap.addAttribute(ResponseBuilder.MESSAGE, message!!)
        }
        return dataMap
    }

    companion object {
        const val DATA = "data"
        const val ERROR = "error"
        const val SUCCESS = "success"
        const val MESSAGE = "message"
        const val STATUS_CODE = "code"
        const val TRACE = "trace"

        fun create(): ResponseBuilder {
            return ResponseBuilder()
        }

    }
}