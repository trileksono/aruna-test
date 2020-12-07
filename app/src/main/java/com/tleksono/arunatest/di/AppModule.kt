package com.tleksono.arunatest.di

import android.app.Application
import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Room
import com.tleksono.arunatest.Apps
import com.tleksono.arunatest.data.source.local.AppDatabases
import com.tleksono.arunatest.data.source.local.PostDao
import com.tleksono.arunatest.data.source.remote.ApiServices
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by trileksono on 07/12/20
 */
@Module
class AppModule {


    @Provides
    @Singleton
    fun bindApplicationContext(application: Apps): Context = application

    //region network
    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkhttp(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    @Singleton
    fun provideApiServices(retrofit: Retrofit): ApiServices =
        retrofit.create(ApiServices::class.java)
    //endregion

    // region cache
    @Provides
    @Singleton
    fun provideDatabase(@NonNull application: Application): AppDatabases {
        return Room
            .databaseBuilder(application, AppDatabases::class.java, "aruna-test.db")
            .build()
    }

    @Provides
    @Singleton
    fun providePostDao(@NonNull database: AppDatabases): PostDao = database.postDao()
    //endregion

}
