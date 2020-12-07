package com.tleksono.arunatest.data.source.remote

import com.google.gson.annotations.SerializedName
import com.tleksono.arunatest.domain.model.Post


/**
 * Created by trileksono on 07/12/20
 */
data class PostResponse(
    @SerializedName("body")
    val body: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("userId")
    val userId: Int?
) {
    fun toPost(): Post {
        return Post(id, userId ?: 0, title.orEmpty(), body.orEmpty())
    }
}
