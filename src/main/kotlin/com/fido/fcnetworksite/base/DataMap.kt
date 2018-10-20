package com.fido.fcnetworksite.base

import org.springframework.util.Assert
import java.util.LinkedHashMap

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/19 22:32
 * @description:
 */
class DataMap : LinkedHashMap<String, Any> {

    constructor()

    constructor(attributeName: String, attributeValue: Any) {
        addAttribute(attributeName, attributeValue)
    }

    fun addAttribute(attributeName: String, attributeValue: Any): DataMap {
        Assert.notNull(attributeName, "key不能为空")
        put(attributeName, attributeValue)
        return this
    }
}
