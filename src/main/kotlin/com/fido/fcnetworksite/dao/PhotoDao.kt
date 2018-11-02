package com.fido.fcnetworksite.dao

import com.fido.fcnetworksite.entity.PhotoEntity
import org.apache.ibatis.annotations.Param

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 13:00 2018/11/2
 */
interface PhotoDao {

    fun select(@Param("moodId") moodId: Int): List<PhotoEntity>

    fun insert(@Param("entities") entities: List<PhotoEntity>)

}
