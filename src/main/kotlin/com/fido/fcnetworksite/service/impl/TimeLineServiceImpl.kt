package com.fido.fcnetworksite.service.impl

import com.fido.fcnetworksite.service.TimeLineService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.ZSetOperations
import org.springframework.stereotype.Service

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/11/12 21:26
 * @description:
 */
@Service
class TimeLineServiceImpl : TimeLineService {
    @Autowired
    private lateinit var zSetOperations: ZSetOperations<String, Any>
    private val TIME_LINE_NAMESPACE = "time_line:"
    private val TIME_LINE_0_NAMESPACE = "time_line_0:"
    override fun add(userId: Long, moodId: Int) {
        val key = buildKey(userId)
        zSetOperations.add(key, moodId, System.currentTimeMillis().toDouble())
        val size = zSetOperations.zCard(key)
        val MAX_NUM: Long = 100
        if (size > MAX_NUM) {
            zSetOperations.removeRange(key, -1, -1)
        }
    }

    override fun getFollow(userId: Long, followingList: List<Long>): List<Int> {
        val key0 = buildKey1(userId)
        var lastTime = 0.0
        if (zSetOperations.zCard(key0) != 0L) {
            val sets = zSetOperations.rangeByScoreWithScores(key0, -1.0, -1.0)
            sets.forEach { lastTime = it.score }
        }
        val followingListAndMySelf = followingList.toMutableList()
        followingListAndMySelf.add(userId)
        followingListAndMySelf.forEach {
            val key = buildKey(it)
            val sets = zSetOperations.rangeByScoreWithScores(key, lastTime, Double.MAX_VALUE)
            sets.forEach { zSetOperations.add(key0, it.value, it.score) }
        }
        val MAX_NUM = 20
        val size = zSetOperations.zCard(key0)
        zSetOperations.removeRange(key0, 0, size - MAX_NUM)

        val list = zSetOperations.rangeWithScores(key0, 0, -1).toMutableList()
        list.sortWith(compareBy { it.score })
        val res = mutableListOf<Int>()
        list.forEach { res.add(it.value as Int) }
        return res
    }

    override fun remove(userId: Long, moodId: Int) {
        val key = buildKey(userId)
        zSetOperations.remove(key, moodId)
    }

    private fun buildKey(userId: Long): String {
        return TIME_LINE_NAMESPACE + userId
    }

    private fun buildKey1(userId: Long): String {
        return TIME_LINE_0_NAMESPACE + userId
    }
}