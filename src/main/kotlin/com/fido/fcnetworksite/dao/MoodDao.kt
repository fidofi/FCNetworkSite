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
    fun insertMood(moodEntity: MoodEntity): Int

    fun selectMoodLikeContent(@Param("content") content: String): List<MoodEntity>

    fun updateMoodInfo(@Param("moodId") moodId: Int,
                       @Param("commentCount") commentCount: Long,
                       @Param("likeCount") likeCount: Long)

    fun selectMoodByUserId(@Param("userId") userId: Long): List<MoodEntity>

    fun countMoodByUserId(@Param("userId") userId: Long): Long

    fun countMoodLikeContent(@Param("content") content: String): Long

    fun deleteMood(@Param("moodId") moodId: Int)

    fun selectByMoodIdList(@Param("moodIdList") moodIdList: List<Int>): List<MoodEntity>

    fun batchUpdateMoodInfo(@Param("entities") moodList: List<MoodEntity>)

    fun selectPopularMoodList(@Param("commentCount") commentCount: Long,
                              @Param("likeCount") likeCount: Long): List<MoodEntity>

    fun countPopularMood(@Param("commentCount") commentCount: Long,
                         @Param("likeCount") likeCount: Long): Long

    fun selectMoodByCondition(@Param("state") state: Int,
                              @Param("userId") userId: Long?,
                              @Param("content") content: String?,
                              @Param("start") start: Int,
                              @Param("pageSize") pageSize: Int): List<MoodEntity>

    fun getTotalByCondition(@Param("state") state: Int,
                            @Param("userId") userId: Long?,
                            @Param("content") content: String?): Long

    fun updateMoodState(@Param("state") state: Int,
                        @Param("moodId") moodId: Int)
}