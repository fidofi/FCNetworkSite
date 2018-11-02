package com.fido.fcnetworksite.dao

import org.apache.ibatis.annotations.Param

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 13:00 2018/11/2
 */
interface PhotoDao {
    fun getPhotoList(@Param("moodId") moodId: Int)
}
