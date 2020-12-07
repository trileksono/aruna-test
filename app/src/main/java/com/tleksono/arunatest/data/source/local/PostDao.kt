package com.tleksono.arunatest.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by trileksono on 07/12/20
 */
@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(movies: List<PostEntity>)

    @Query("select * from post where title like :title")
    fun getPostByTitle(title: String): List<PostEntity>

    @Query("select * from post")
    fun getAllPost() : List<PostEntity>
}