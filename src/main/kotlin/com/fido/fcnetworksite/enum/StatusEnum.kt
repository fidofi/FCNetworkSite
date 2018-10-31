package com.fido.fcnetworksite.enum

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/19 22:25
 * @description:
 */
enum class StatusEnum(val code: Int, val message: String) {

    /**
     * 请求成功
     */
    SUCCESS(200, "Success"),
    /**
     * 请求资源不存在
     */
    RESOURCE_NOT_FOUND(40400, "Request Resource Not Exist"),
    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(50000, "Server Internal Error"),
    /**
     * 请求参数非法
     */
    REQUEST_PARAMS_NOT_VALID(40000, "Request Params Illegal"),
    /**
     * 请求参数有误
     */

    ILLEGAL_PARAMETER_ERROR(40001, "Illegal Parameter Error"),
    /**
     * 操作有误
     */

    ILLEGAL_OPERATION(40002, "Illegal Operation Error"),

    //用户相关
    /**
     * 密码不匹配
     */
    PASSWORD_NOT_MATCH(40003, "Password Not Match"),

    /**
     * 邮箱已经存在
     */
    EMAIL_REPEAT(40004, "Email already exist"),
    /**
     * 昵称已经存在
     */
    NICK_NAME_REPEAT(40005, "Nick name already exist"),
    /**
     * 用户名不存在
     */

    USER_NOT_EXIST(40006, "User not exist")

}