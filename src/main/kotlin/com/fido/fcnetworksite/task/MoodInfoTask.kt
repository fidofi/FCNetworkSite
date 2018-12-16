package com.fido.fcnetworksite.task

import com.fido.fcnetworksite.constant.COMMENT_PREFIX
import com.fido.fcnetworksite.constant.LIKE_PREFIX
import com.fido.fcnetworksite.dao.MoodDao
import com.fido.fcnetworksite.entity.MoodEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component


/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 20:28 2018/12/12
 */
@Component
class MoodInfoTask {
    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String, Any>
    @Autowired
    private lateinit var moodDao: MoodDao

    /**
     * 定时任务，同步redis数据到数据库
     */
    @Scheduled(cron = "0 */10 * * * ?")
    fun synCommentAndLikeCount() {
        val commentKeys = redisTemplate.keys("$COMMENT_PREFIX*")
        val moodInfoMap = mutableMapOf<Int, MoodEntity>()
        commentKeys.forEach {
            val moodId = it.substringAfter(":").toInt()
            val commentCount = redisTemplate.opsForValue().get(it).toString().toLong()
            moodInfoMap[moodId] = MoodEntity(moodId, commentCount, 0)
        }
        val likeKeys = redisTemplate.keys("$LIKE_PREFIX*")
        likeKeys.forEach {
            val moodId = it.substringAfter(":").toInt()
            val likeCount = redisTemplate.opsForList().size(it)
            if (moodInfoMap[moodId] == null) {
                moodInfoMap[moodId] = MoodEntity(moodId, 0, likeCount)
            } else {
                moodInfoMap[moodId]!!.likeCount = likeCount
            }
        }
        if (moodInfoMap.values.isNotEmpty())
            moodDao.batchUpdateMoodInfo(moodInfoMap.values.toList())
        //清空缓存，重新计数
        redisTemplate.delete("$COMMENT_PREFIX*")
        redisTemplate.delete("$LIKE_PREFIX*")
    }
}