package com.fido.fcnetworksite.service.impl

import com.fido.fcnetworksite.dao.RelationDao
import com.fido.fcnetworksite.entity.RelationEntity
import com.fido.fcnetworksite.service.RelationService
import com.fido.fcnetworksite.service.UserService
import com.fido.fcnetworksite.vo.PageInfoVo
import com.fido.fcnetworksite.vo.UserBaseInfoVo
import com.github.pagehelper.PageHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 19:19 2018/11/8
 */

@Service
class RelationServiceImpl : RelationService {
    @Autowired
    private lateinit var relationDao: RelationDao
    @Autowired
    private lateinit var userService: UserService

    /**
     * 关注某人
     */
    override fun followSomeOne(followingId: Long, followedId: Long) {
        relationDao.insert(RelationEntity(followingId, followedId))
    }

    /**
     * 取关某人
     */
    override fun unFollowSomeOne(followingId: Long, followedId: Long) {
        relationDao.delete(followingId, followedId)
    }

    /**
     * 获取粉丝列表
     */
    override fun getFans(userId: Long, pageIndex: Int, pageSize: Int): PageInfoVo<UserBaseInfoVo> {
        PageHelper.startPage<RelationEntity>(pageIndex, pageSize)
        val relationList = relationDao.getFans(userId)
        val total = relationDao.countFans(userId)
        var userIdList = relationList.map { it.followingId }.toMutableList()
        userIdList.add(userId)
        val userInfoMap = userService.batchSelectUser(userIdList).map { it.userId to it }.toMap()
        return PageInfoVo(total, pageIndex, pageSize, userIdList.map {
            UserBaseInfoVo(it, userInfoMap[it]!!.nickName, userInfoMap[it]!!.photoUrl, userInfoMap[it]!!.introduction)
        })
    }

    /**
     * 获取关注人列表
     */
    override fun getFollowingList(userId: Long, pageIndex: Int, pageSize: Int): PageInfoVo<UserBaseInfoVo> {
        PageHelper.startPage<RelationEntity>(pageIndex, pageSize)
        val relationList = relationDao.getFollowingUsers(userId)
        val total = relationDao.countFollowing(userId)
        var userIdList = relationList.map { it.followingId }.toMutableList()
        userIdList.add(userId)
        val userInfoMap = userService.batchSelectUser(userIdList).map { it.userId to it }.toMap()
        return PageInfoVo(total, pageIndex, pageSize, userIdList.map {
            UserBaseInfoVo(it, userInfoMap[it]!!.nickName, userInfoMap[it]!!.photoUrl, userInfoMap[it]!!.introduction)
        })
    }

    override fun getAllFollowingIdList(userId: Long): List<Long> {
        return relationDao.getAllFollowingIdList(userId)
    }
}