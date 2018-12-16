package com.fido.fcnetworksite.service.impl

import com.fido.fcnetworksite.dao.RelationDao
import com.fido.fcnetworksite.entity.RelationEntity
import com.fido.fcnetworksite.service.RelationService
import com.fido.fcnetworksite.service.TimeLineService
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
    @Autowired
    private lateinit var timeLineService: TimeLineService

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
        val userInfoMap = userService.batchSelectUser(userIdList).map { it.userId to it }.toMap()
        val userBaseInfoVoList = mutableListOf<UserBaseInfoVo>()
        userIdList.forEach {
            var state = 0
            if (relationDao.getRelation(userId, it) != null) {
                state = 2
            }
            userBaseInfoVoList.add(UserBaseInfoVo(it, userInfoMap[it]!!.nickName, userInfoMap[it]!!.photoUrl, userInfoMap[it]!!.introduction, state))
        }
        return PageInfoVo(total, pageIndex, pageSize, userBaseInfoVoList)
    }

    /**
     * 获取关注人列表
     */
    override fun getFollowingList(userId: Long, pageIndex: Int, pageSize: Int): PageInfoVo<UserBaseInfoVo> {
        PageHelper.startPage<RelationEntity>(pageIndex, pageSize)
        val relationList = relationDao.getFollowingUsers(userId)
        if (relationList.isEmpty()) {
            return PageInfoVo(0, pageIndex, pageSize, emptyList())
        }
        val total = relationDao.countFollowing(userId)
        var userIdList = relationList.map { it.followingId }.toMutableList()
        val userInfoMap = userService.batchSelectUser(userIdList).map { it.userId to it }.toMap()
        val userBaseInfoVoList = mutableListOf<UserBaseInfoVo>()
        userIdList.forEach {
            var state = 1
            if (relationDao.getRelation(userId, it) != null) {
                state = 2
            }
            userBaseInfoVoList.add(UserBaseInfoVo(it, userInfoMap[it]!!.nickName, userInfoMap[it]!!.photoUrl, userInfoMap[it]!!.introduction, state))
        }
        return PageInfoVo(total, pageIndex, pageSize, userBaseInfoVoList)
    }

    override fun getAllFollowingIdList(userId: Long): List<Long> {
        return relationDao.getAllFollowingIdList(userId)
    }
}