package com.fido.fcnetworksite.service

import com.fido.fcnetworksite.vo.PageInfoVo
import com.fido.fcnetworksite.vo.UserBaseInfoVo

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 19:11 2018/11/8
 */
interface RelationService {
    /**
     * 关注某人
     */
    fun followSomeOne(followingId: Long, followedId: Long)

    /**
     * 取关某人
     */
    fun unFollowSomeOne(followingId: Long, followedId: Long)

    /**
     * 获取粉丝
     */
    fun getFans(userId: Long, pageIndex: Int, pageSize: Int): PageInfoVo<UserBaseInfoVo>

    /**
     * 获取关注的列表
     */

    fun getFollowingList(userId: Long, pageIndex: Int, pageSize: Int): PageInfoVo<UserBaseInfoVo>
}