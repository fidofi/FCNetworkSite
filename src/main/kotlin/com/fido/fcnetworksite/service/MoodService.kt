package com.fido.fcnetworksite.service

import com.fido.fcnetworksite.entity.MoodEntity
import com.fido.fcnetworksite.vo.MoodVo
import com.fido.fcnetworksite.vo.PageInfoVo

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 15:32 2018/10/26
 */
interface MoodService {
    /**
     * 插入心情
     */
    fun insertMood(moodVo: MoodVo)

    fun selectMoodLikeContent(content: String, pageIndex: Int, pageSize: Int): PageInfoVo<MoodVo>

    fun updateMoodInfo(moodId: Int, commentCount: Long, likeCount: Long)

    fun selectMoodByUserId(userId: Long, pageIndex: Int, pageSize: Int): PageInfoVo<MoodVo>

    fun deleteMood(userId: Long, moodId: Int)

    fun selectByMoodIdList(moodIdList: List<Int>): List<MoodVo>

    fun likeMood(userId: Long, moodId: Int)

    fun selectPopularMoodList(pageIndex: Int, pageSize: Int): PageInfoVo<MoodVo>

    fun selectMoodByCondition(state: Int,
                              userName:String,
                              content: String,
                              start: Int,
                              pageSize: Int): List<MoodEntity>
}