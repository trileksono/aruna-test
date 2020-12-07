package com.tleksono.arunatest.view.mainfragment

import androidx.lifecycle.*
import com.tleksono.arunatest.data.source.remote.State
import com.tleksono.arunatest.domain.model.Post
import com.tleksono.arunatest.domain.usecase.GetPostByTitleUseCase
import com.tleksono.arunatest.domain.usecase.GetPostUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by trileksono on 07/12/20
 */
class MainFragmentViewModel @Inject constructor(
    private val getPostByTitleUseCase: GetPostByTitleUseCase,
    private val getPostUseCase: GetPostUseCase
) : ViewModel() {

    private var job: Job? = null

    val postsLiveData: LiveData<State<List<Post>>> get() = mPostLiveData
    private val mPostLiveData = MutableLiveData<State<List<Post>>>()

    @ExperimentalCoroutinesApi
    private val queryStringChannel = BroadcastChannel<String>(Channel.CONFLATED)

    @ExperimentalCoroutinesApi
    @FlowPreview
    val queryStringLiveData = queryStringChannel
        .asFlow()
        .filter { it.isNotEmpty() }
        .debounce(500)
        .flatMapLatest {
            getPostByTitleUseCase.invoke(it)
        }.asLiveData()


    fun fetchData() {
        job = viewModelScope.launch {
            getPostUseCase.invoke(null)
                .collect {
                    mPostLiveData.postValue(it)
                }
        }
    }

    @ExperimentalCoroutinesApi
    fun searchQuery(queryString: String) {
        queryStringChannel.offer(queryString)
    }


}
