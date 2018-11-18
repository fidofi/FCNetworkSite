package com.fido.fcnetworksite.vo

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/11/18 14:51
 * @description:
 */
data class UpdatePasswordVo(val email: String, val oldPassword: String, val newPassword: String)