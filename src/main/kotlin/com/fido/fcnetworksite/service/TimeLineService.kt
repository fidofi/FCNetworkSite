package com.fido.fcnetworksite.service


/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/11/12 21:21
 * @description:时间线相关的功能
 */
interface TimeLineService {

    fun add(userId: Long, moodId: Int)

    fun getFollow(userId: Long, followingList: List<Long>): List<Int>

    fun remove(userId: Long, moodId: Int)
}