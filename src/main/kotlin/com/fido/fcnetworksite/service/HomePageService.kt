package com.fido.fcnetworksite.service

import com.fido.fcnetworksite.vo.MoodVo

/**
 * @author: Fido Wang (fromwxf@gmail.com)
 * @date: 2018/11/12 22:21
 * @description:
 */
interface HomePageService {

    fun getHomePage(userId: Long): List<MoodVo>
}