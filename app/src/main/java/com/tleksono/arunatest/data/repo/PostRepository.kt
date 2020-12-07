package com.tleksono.arunatest.data.repo

import com.tleksono.arunatest.data.source.remote.State
import com.tleksono.arunatest.domain.model.Post
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

/**
 * Created by trileksono on 07/12/20
 */
interface PostRepository {

    fun getPost(dispatcher: CoroutineContext): Flow<State<List<Post>>>

    fun getPostByTitle(title: String, dispatcher: CoroutineContext): Flow<State<List<Post>>>

    fun savePost(posts: List<Post>, dispatcher: CoroutineContext)

    fun getAllPost() : List<Post>
}