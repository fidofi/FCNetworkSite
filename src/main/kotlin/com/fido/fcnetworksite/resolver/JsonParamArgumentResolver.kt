package com.fido.fcnetworksite.resolver

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fido.fcnetworksite.enum.StatusEnum
import com.fido.fcnetworksite.exception.BaseException
import com.fido.fcnetworksite.util.JsonUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import java.io.IOException
import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest
import kotlin.reflect.KClass

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/27 20:47
 * @description:
 */
const val JSON_REQUEST_BODY = "JSON_REQUEST_BODY"

class JsonParamArgumentResolver : HandlerMethodArgumentResolver {
    val objectMapper = jacksonObjectMapper()

    private val logger: Logger = LoggerFactory.getLogger(JsonParamArgumentResolver::class.java)
    override fun supportsParameter(parameter: MethodParameter?): Boolean {
        return parameter!!.hasParameterAnnotation(JsonParam::class.java)
    }

    override fun resolveArgument(parameter: MethodParameter?, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest?, binderFactory: WebDataBinderFactory?): Any? {
        val paramType = parameter!!.parameterType
        val annotation = parameter.getParameterAnnotation(JsonParam::class.java)
        val paramName = if (annotation.value.isNotEmpty()) annotation.value else parameter.parameterName
        val jsonObj = getRequestBody(webRequest!!)
        logger.info("jsonObj1:{}",jsonObj)
        if (annotation.required && jsonObj?.containsKey(paramName) != true) {
            throw JsonParamMissingException(paramName)
        }

        if (annotation.list) {
            return JsonUtils.jsonToList(jsonObj?.get(paramName), annotation.rawType.java)
        } else {
            return JsonUtils.beanToBean(jsonObj?.get(paramName), paramType)
        }
    }


    private fun getRequestBody(webRequest: NativeWebRequest): Map<String, Any>? {
        val servletRequest = webRequest.getNativeRequest(HttpServletRequest::class.java)

        var jsonBodyObj: Map<String, Any>? = null
        if (servletRequest.getAttribute(JSON_REQUEST_BODY) != null) {
            jsonBodyObj = servletRequest.getAttribute(JSON_REQUEST_BODY) as Map<String, Any>?
        }
        if (jsonBodyObj == null) {
            try {
                val jsonBody = servletRequest.reader.lines().collect(Collectors.joining(System.lineSeparator()))
                logger.info("jsonbody为:{}", jsonBody)
                jsonBodyObj = JsonUtils.jsonToMap(jsonBody)
                logger.info("jsonobj为:{}", jsonBodyObj)
                servletRequest.setAttribute(JSON_REQUEST_BODY, jsonBodyObj)
            } catch (e: IOException) {
                throw RuntimeException(e)
            }

        }
        return jsonBodyObj
    }
}

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class JsonParam(val value: String = "", val required: Boolean = true, val list: Boolean = false, val rawType: KClass<out Any> = Any::class)

class JsonParamMissingException(paramName: String) : BaseException(StatusEnum.REQUEST_PARAMS_NOT_VALID,
        "请求 body 中缺少 \"$paramName\" 字段")