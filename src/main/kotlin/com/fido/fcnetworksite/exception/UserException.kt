package com.fido.fcnetworksite.exception

import com.fido.fcnetworksite.base.DataMap
import com.fido.fcnetworksite.enum.StatusEnum
import com.fido.fcnetworksite.util.ResponseBuilder

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/20 19:37
 * @description:
 */
class UserException(statusEnum: StatusEnum) : BaseException(statusEnum) {
    override fun toResponse(): DataMap {
        return ResponseBuilder().error(code).message(message).build()
    }
}