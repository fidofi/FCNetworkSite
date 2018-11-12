package com.fido.fcnetworksite.service.impl

import com.fido.fcnetworksite.service.HomePageService
import com.fido.fcnetworksite.service.MoodService
import com.fido.fcnetworksite.service.RelationService
import com.fido.fcnetworksite.service.TimeLineService
import com.fido.fcnetworksite.vo.MoodVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 *
 * @date: 2018/11/12 22:36
 * @description:
 */
@Service
class HomePageServiceImpl : HomePageService {
    @Autowired
    private lateinit var timeService: TimeLineService
    @Autowired
    private lateinit var moodService: MoodService
    @Autowired
    private lateinit var relationService: RelationService

    override fun getHomePage(userId: Long): List<MoodVo> {
        //关注人Id
        val followingIdList = relationService.getAllFollowingIdList(userId)
        val moodIdList = timeService.getFollow(userId, followingIdList)
        return moodService.selectByMoodIdList(moodIdList)
    }
}