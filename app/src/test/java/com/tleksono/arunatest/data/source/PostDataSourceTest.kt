package com.tleksono.arunatest.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tleksono.arunatest.TestUtil
import com.tleksono.arunatest.data.repo.PostRepository
import com.tleksono.arunatest.data.source.local.PostDao
import com.tleksono.arunatest.data.source.remote.ApiServices
import com.tleksono.arunatest.data.source.remote.PostResponse
import com.tleksono.arunatest.data.source.remote.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Response

/**
 * Created by trileksono on 08/12/20
 */
@RunWith(JUnit4::class)
class PostDataSourceTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val testDispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var apiServices: ApiServices

    @Mock
    lateinit var postDao: PostDao
    lateinit var postRepository: PostRepository

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        postRepository = PostDataSource(apiServices, postDao)
    }

    @Test
    fun `test getPost success`() {
        val call = mock<Call<List<PostResponse>>>()
        `when`(call.execute())
            .thenReturn(Response.success(TestUtil.generateFakeResponse()))
        `when`(apiServices.getPost()).thenReturn(call)
        `when`(postDao.insertPost(TestUtil.generateFakeEntity())).then { null }

        runBlocking {
            val flow = postRepository.getPost(testDispatcher).toList()
            Assert.assertEquals(
                flow,
                listOf(State.Loading, State.Success(TestUtil.generateFakePost()))
            )
        }
    }

    @Test
    fun `test get postByTitle success`() {
        `when`(postDao.getPostByTitle("")).thenReturn(TestUtil.generateFakeEntity())
        runBlocking {
            val flow = postRepository.getPostByTitle("", testDispatcher).toList()
            Assert.assertEquals(
                flow,
                listOf(State.Loading, State.Success(TestUtil.generateFakePost()))
            )
        }
    }


    inline fun <reified T> mock(): T = mock(T::class.java)

}