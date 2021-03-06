package com.fido.fcnetworksite.service

import com.fido.fcnetworksite.vo.MoodVo
import com.fido.fcnetworksite.vo.PageInfoVo
import com.fido.fcnetworksite.vo.UserVo

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 11:27 2018/12/19
 */
interface ManagerService {
    fun selectUser(state: Int, userName: String?, page: Int, pageSize: Int): PageInfoVo<UserVo>

    fun selectMood(state: Int, userName: String?, content: String?, page: Int, pageSize: Int): PageInfoVo<MoodVo>

    fun passUser(userId: Long)

    fun unPassUser(userId: Long)

    fun unPassMood(moodId: Int)

    fun passMood(moodId: Int)

    fun freezeUser(userId: Long)

    fun unFreezeUser(userId: Long)
}