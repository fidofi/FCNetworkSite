package com.fido.fcnetworksite.handler

import com.fido.fcnetworksite.base.DataMap
import com.fido.fcnetworksite.exception.BaseException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/20 21:47
 * @description:
 */
@ControllerAdvice
class GloablExceptionHandler {

    @ExceptionHandler(BaseException::class)
    fun businessException(exception: BaseException): DataMap {
        return exception.toResponse()
    }

}