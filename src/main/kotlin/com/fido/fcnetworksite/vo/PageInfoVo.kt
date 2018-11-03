package com.fido.fcnetworksite.vo

import java.io.Serializable

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 15:51 2018/10/26
 */
class PageInfoVo<T>(val total: Long, val pageIndex: Int, val pageSize: Int, val data: List<T>) : Serializable