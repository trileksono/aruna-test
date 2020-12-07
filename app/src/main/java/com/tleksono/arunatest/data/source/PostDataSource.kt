package com.tleksono.arunatest.data.source

import com.tleksono.arunatest.data.repo.PostRepository
import com.tleksono.arunatest.data.source.local.PostDao
import com.tleksono.arunatest.data.source.remote.ApiServices
import com.tleksono.arunatest.data.source.remote.State
import com.tleksono.arunatest.domain.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by trileksono on 07/12/20
 */
class PostDataSource @Inject constructor(
    private val apiServices: ApiServices,
    private val postDao: PostDao
) : PostRepository {

    override fun getPost(dispatcher: CoroutineContext): Flow<State<List<Post>>> = flow {
        emit(State.Loading)
        val result = try {
            val response = apiServices.getPost().execute()
            when (response.isSuccessful && response.body() != null) {
                true -> {
                    val listPost = response.body()!!.map { it.toPost() }
                    savePost(listPost, dispatcher)
                    State.Success(listPost)
                }
                false -> {
                    val listPost = getAllPost()
                    if (listPost.isEmpty()) {
                        State.Failure("Tidak dapat terkoneksi dengan server")
                    } else {
                        State.Success(listPost)
                    }
                }
            }
        } catch (e: IOException) {
            val listPost = getAllPost()
            if (listPost.isEmpty()) {
                State.Failure(e.localizedMessage.orEmpty())
            } else {
                State.Success(listPost)
            }
        }
        emit(result)
    }.flowOn(dispatcher)

    override fun getPostByTitle(
        title: String,
        dispatcher: CoroutineContext
    ): Flow<State<List<Post>>> = flow {
        emit(State.Loading)
        val data = postDao.getPostByTitle(title).map { it.toPost() }
        emit(State.Success(data))
    }.flowOn(dispatcher)

    override fun savePost(posts: List<Post>, dispatcher: CoroutineContext) {
        postDao.insertPost(posts.filter { it.id != null && it.id > 0 }.map { it.toEntity() })
    }

    override fun getAllPost(): List<Post> {
        return postDao.getAllPost().map { it.toPost() }
    }
}
