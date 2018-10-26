package com.fido.fcnetworksite.dao

import com.fido.fcnetworksite.entity.MoodEntity
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/10/25 22:14
 * @description:
 */
@Repository
interface MoodDao {
    fun insertMood(moodEntity: MoodEntity)

    fun selectMoodLikeContent(@Param("content") content: String): List<MoodEntity>

    fun updateMoodInfo(@Param("moodId") moodId: Int,
                       @Param("commentCount") commentCount: Long,
                       @Param("likeCount") likeCount: Long)

    fun selectMoodByUserId(@Param("userId") userId: Long): List<MoodEntity>

    fun deleteMood(@Param("moodId") moodId: Int)
}