package com.tleksono.arunatest.domain.usecase

import com.tleksono.arunatest.data.repo.PostRepository
import com.tleksono.arunatest.data.source.remote.State
import com.tleksono.arunatest.domain.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val repository: PostRepository
) : BaseUseCase<Any?, Flow<State<List<Post>>>> {
    override suspend fun invoke(params: Any?): Flow<State<List<Post>>> {
        return repository.getPost(Dispatchers.IO)
    }
}