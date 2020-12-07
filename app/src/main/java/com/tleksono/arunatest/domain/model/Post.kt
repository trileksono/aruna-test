package com.tleksono.arunatest.domain.model

import com.tleksono.arunatest.data.source.local.PostEntity

/**
 * Created by trileksono on 07/12/20
 */
data class Post(
    val id: Int?,
    val userId: Int,
    val title: String,
    val body: String
) {
    fun toEntity(): PostEntity {
        return PostEntity(id ?: -1, userId, title, body)
    }
}
