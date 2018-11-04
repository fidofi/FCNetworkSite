package com.fido.fcnetworksite.service.impl

import com.fido.fcnetworksite.dao.MoodDao
import com.fido.fcnetworksite.entity.MoodEntity
import com.fido.fcnetworksite.service.MoodService
import com.fido.fcnetworksite.service.PhotoService
import com.fido.fcnetworksite.service.UserService
import com.fido.fcnetworksite.vo.MoodVo
import com.fido.fcnetworksite.vo.PageInfoVo
import com.github.pagehelper.PageHelper
import org.springframework.beans.factory.annotation.Autowired
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

    override fun insertMood(moodVo: MoodVo) {
        val moodEntity = MoodEntity(moodVo.content, moodVo.userId)
        moodDao.insertMood(moodEntity)
        if (moodVo.photoList != null && moodVo.photoList.isNotEmpty())
            photoService.batchInsert(moodEntity.moodId, moodVo.photoList)
    }

    override fun selectMoodLikeContent(content: String, pageIndex: Int, pageSize: Int): PageInfoVo<MoodVo> {
        PageHelper.startPage<MoodEntity>(pageIndex, pageSize)
        val moodList = moodDao.selectMoodLikeContent(content)
        val total = moodDao.countMoodLikeContent(content)
        val userIdList = moodList.map { it.userId }.toSet().toList()
        val userNameMap = userService.batchSelectUser(userIdList).associateBy({ it.userId }, { it.nickName }).toMap()
        val photoMap = photoService.batchSelectByMoodId(moodList.map { it.moodId })
        return PageInfoVo(total, pageIndex, pageSize, moodList.map {
            MoodVo(it.moodId, it.userId, userNameMap[it.userId]!!,
                    it.content, it.commentCount, it.likeCount, photoMap[it.moodId])
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
        val userNameMap = userService.batchSelectUser(userIdList).associateBy({ it.userId }, { it.nickName }).toMap()
        val photoMap = photoService.batchSelectByMoodId(moodList.map { it.moodId })
        return PageInfoVo(total, pageIndex, pageSize, moodList.map {
            MoodVo(it.moodId, it.userId, userNameMap[it.userId]!!,
                    it.content, it.commentCount, it.likeCount, photoMap[it.moodId])
        }
        )
    }

    override fun deleteMood(moodId: Int) {
        moodDao.deleteMood(moodId)
    }
}