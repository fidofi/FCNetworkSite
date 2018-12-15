package com.fido.fcnetworksite.service.impl

import com.fido.fcnetworksite.constant.LIKE_PREFIX
import com.fido.fcnetworksite.dao.MoodDao
import com.fido.fcnetworksite.entity.MoodEntity
import com.fido.fcnetworksite.service.MoodService
import com.fido.fcnetworksite.service.PhotoService
import com.fido.fcnetworksite.service.TimeLineService
import com.fido.fcnetworksite.service.UserService
import com.fido.fcnetworksite.vo.MoodVo
import com.fido.fcnetworksite.vo.PageInfoVo
import com.github.pagehelper.PageHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.ListOperations
import org.springframework.stereotype.Service

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/30 22:58
 * @description:
 */
@Service
class MoodServiceImpl : MoodService {
    @Autowired
    private lateinit var moodDao: MoodDao
    @Autowired
    private lateinit var photoService: PhotoService
    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var timeLineService: TimeLineService
    @Autowired
    private lateinit var listOperations: ListOperations<String, Any>

    override fun insertMood(moodVo: MoodVo) {
        val moodEntity = MoodEntity(moodVo.content, moodVo.userId)
        moodDao.insertMood(moodEntity)
        if (moodVo.photoList != null && moodVo.photoList.isNotEmpty()) {
            photoService.batchInsert(moodEntity.moodId, moodVo.photoList)
        }
        timeLineService.add(moodEntity.userId, moodEntity.moodId)
    }

    override fun selectMoodLikeContent(content: String, pageIndex: Int, pageSize: Int): PageInfoVo<MoodVo> {
        PageHelper.startPage<MoodEntity>(pageIndex, pageSize)
        val moodList = moodDao.selectMoodLikeContent(content)
        val total = moodDao.countMoodLikeContent(content)
        val userIdList = moodList.map { it.userId }.toSet().toList()
        val userNameMap = userService.batchSelectUser(userIdList).associateBy({ it.userId }, { it }).toMap()
        val photoMap = photoService.batchSelectByMoodId(moodList.map { it.moodId })
        return PageInfoVo(total, pageIndex, pageSize, moodList.map {
            MoodVo(it.moodId, it.userId, userNameMap[it.userId]!!.nickName,
                    it.content, it.commentCount,
                    it.likeCount, userNameMap[it.userId]!!.photoUrl, photoMap[it.moodId], it.createTime)
        }
        )
    }

    override fun updateMoodInfo(moodId: Int, commentCount: Long, likeCount: Long) {
        moodDao.updateMoodInfo(moodId, commentCount, likeCount)
    }

    override fun selectMoodByUserId(userId: Long, pageIndex: Int, pageSize: Int): PageInfoVo<MoodVo> {
        PageHelper.startPage<MoodEntity>(pageIndex, pageSize)
        val moodList = moodDao.selectMoodByUserId(userId)
        val total = moodDao.countMoodByUserId(userId)
        val userIdList = moodList.map { it.userId }.toSet().toList()
        val userNameMap = userService.batchSelectUser(userIdList).map { it.userId to it }.toMap()
        val photoMap = photoService.batchSelectByMoodId(moodList.map { it.moodId })
        return PageInfoVo(total, pageIndex, pageSize, moodList.map {
            MoodVo(it.moodId, it.userId, userNameMap[it.userId]!!.nickName,
                    it.content, it.commentCount,
                    it.likeCount, userNameMap[it.userId]!!.photoUrl, photoMap[it.moodId], it.createTime)
        }
        )
    }

    override fun deleteMood(userId: Long, moodId: Int) {
        moodDao.deleteMood(moodId)
        timeLineService.remove(userId, moodId)
    }

    override fun selectByMoodIdList(moodIdList: List<Int>): List<MoodVo> {
        if (moodIdList.isEmpty()) {
            return emptyList()
        }
        val moodList = moodDao.selectByMoodIdList(moodIdList)
        val userIdList = moodList.map { it.userId }.toSet().toList()
        val userMap = userService.batchSelectUser(userIdList).map { it.userId to it }.toMap()
        val photoMap = photoService.batchSelectByMoodId(moodList.map { it.moodId })
        return moodList.map {
            MoodVo(it.moodId, it.userId, userMap[it.userId]!!.nickName,
                    it.content, it.commentCount,
                    it.likeCount, userMap[it.userId]!!.photoUrl, photoMap[it.moodId], it.createTime)
        }
    }

    /**
     * 点赞
     */
    override fun likeMood(userId: Long, moodId: Int) {
        val key = LIKE_PREFIX + moodId
        listOperations.rightPush(key, userId)
    }

    override fun selectPopularMoodList(pageIndex: Int, pageSize: Int): PageInfoVo<MoodVo> {
        PageHelper.startPage<MoodEntity>(pageIndex, pageSize)
        val moodList = moodDao.selectPopularMoodList(3, 3)
        val total = moodDao.countPopularMood(3, 3)
        val userIdList = moodList.map { it.userId }.toSet().toList()
        val userNameMap = userService.batchSelectUser(userIdList).map { it.userId to it }.toMap()
        val photoMap = photoService.batchSelectByMoodId(moodList.map { it.moodId })
        return PageInfoVo(total, pageIndex, pageSize, moodList.map {
            MoodVo(it.moodId, it.userId, userNameMap[it.userId]!!.nickName,
                    it.content, it.commentCount,
                    it.likeCount, userNameMap[it.userId]!!.photoUrl, photoMap[it.moodId], it.createTime)
        }
        )
    }
}