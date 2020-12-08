package com.tleksono.arunatest

import com.tleksono.arunatest.data.source.local.PostEntity
import com.tleksono.arunatest.data.source.remote.PostResponse
import com.tleksono.arunatest.domain.model.Post

/**
 * Created by trileksono on 08/12/20
 */
class TestUtil {

    companion object {
        fun generateFakeResponse(): List<PostResponse> {
            val list = mutableListOf<PostResponse>()
            for (i in 1..3) {
                list.add(PostResponse(
                    id = i,
                    userId = i,
                    title = "title $i",
                    body = "body $i"
                ))
            }
            return list
        }

        fun generateFakeEntity(): List<PostEntity> {
            val list = mutableListOf<PostEntity>()
            for (i in 1..3) {
                list.add(
                    PostEntity(
                        id = i,
                        userId = i,
                        title = "title $i",
                        body = "body $i"
                    )
                )
            }
            return list
        }

        fun generateFakePost(): List<Post> {
            val list = mutableListOf<Post>()
            for (i in 1..3) {
                list.add(Post(
                    id = i,
                    userId = i,
                    title = "title $i",
                    body = "body $i"
                ))
            }
            return list
        }
    }

}