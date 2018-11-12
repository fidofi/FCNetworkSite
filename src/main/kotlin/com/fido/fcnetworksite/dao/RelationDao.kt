package com.fido.fcnetworksite.dao

import com.fido.fcnetworksite.entity.RelationEntity
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

/**
 * @author: wangxianfei
 * @desription:
 * @date: Created in 18:51 2018/11/8
 */
@Repository
interface RelationDao {

    fun getFans(@Param("followedId") followedId: Long): List<RelationEntity>

    fun getFollowingUsers(@Param("followingId") followingId: Long): List<RelationEntity>

    fun insert(relationEntity: RelationEntity)

    fun getRelation(@Param("followingId") followingId: Long, @Param("followedId") followedId: Long): RelationEntity

    fun delete(@Param("followingId") followingId: Long, @Param("followedId") followedId: Long)

    fun countFans(@Param("userId") userId: Long): Long

    fun countFollowing(@Param("userId") userId: Long): Long

    fun getAllFollowingIdList(@Param("userId") userId: Long): List<Long>
}