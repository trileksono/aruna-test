package com.tleksono.arunatest.data.source.remote

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by trileksono on 07/12/20
 */
interface ApiServices {

    @GET("posts")
    fun getPost(): Call<List<PostResponse>>

}
