package com.fido.fcnetworksite.handler

import com.fido.fcnetworksite.base.DataMap
import com.fido.fcnetworksite.enum.StatusEnum
import com.fido.fcnetworksite.exception.BaseException
import com.fido.fcnetworksite.util.ResponseBuilder
import com.fido.fcnetworksite.util.ValidatorUtils
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/20 21:47
 * @description:
 */
@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(BaseException::class)
    fun businessException(exception: BaseException): DataMap {
        return ResponseBuilder.create().code(exception.code).message(exception.message).build()
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(ex: MethodArgumentNotValidException): DataMap {
        return ResponseBuilder().code(StatusEnum.REQUEST_PARAMS_NOT_VALID.code)
                .message(ValidatorUtils.buildErrorMessage(ex.bindingResult))
                .build()
    }
}