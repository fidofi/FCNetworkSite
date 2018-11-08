package com.fido.fcnetworksite.enum

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 18:47 2018/11/8
 */
enum class RelationEnum(val status: Int, val value: String) {

    UNILATERAL_CONCERN(0, "单方面关注"),
    MUTUAL_CONCERN(1, "相互关注")
}