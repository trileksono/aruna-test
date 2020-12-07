package com.tleksono.arunatest.domain.usecase

import com.tleksono.arunatest.data.repo.PostRepository
import com.tleksono.arunatest.data.source.remote.State
import com.tleksono.arunatest.domain.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostByTitleUseCase @Inject constructor(
    private val repository: PostRepository
) : BaseUseCase<String, Flow<State<List<Post>>>> {
    override suspend fun invoke(params: String): Flow<State<List<Post>>> {
        return repository.getPostByTitle("%$params%", Dispatchers.IO)
    }
}