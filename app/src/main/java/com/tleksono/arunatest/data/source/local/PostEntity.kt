package com.tleksono.arunatest.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tleksono.arunatest.domain.model.Post

/**
 * Created by trileksono on 07/12/20
 */
@Entity(tableName = "post")
data class PostEntity(

    @PrimaryKey var id: Int,
    var userId: Int,
    var title: String,
    var body: String

) {
    fun toPost(): Post = Post(id, userId, title, body)
}
