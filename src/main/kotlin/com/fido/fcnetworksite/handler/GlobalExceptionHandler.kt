package com.fido.fcnetworksite.handler

import com.fido.fcnetworksite.base.DataMap
import com.fido.fcnetworksite.exception.BaseException
import com.fido.fcnetworksite.util.ResponseBuilder
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/20 21:47
 * @description:
 */
@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(BaseException::class)
    fun businessException(exception: BaseException): DataMap {
        return exception.toResponse()
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodParamsException(exception: MethodArgumentNotValidException): DataMap {
        val errorMesg = exception.message
        return ResponseBuilder.create().message(errorMesg).build()
    }
}