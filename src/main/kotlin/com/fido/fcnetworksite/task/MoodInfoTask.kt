package com.fido.fcnetworksite.task

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component


/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 20:28 2018/12/12
 */
@Component
class MoodInfoTask {
    @Scheduled(cron = " 0/30 * * * ?")
    fun synCommentAndLikeCount() {

    }
}