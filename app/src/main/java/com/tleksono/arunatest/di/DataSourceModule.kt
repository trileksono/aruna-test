package com.tleksono.arunatest.di

import com.tleksono.arunatest.data.repo.PostRepository
import com.tleksono.arunatest.data.source.PostDataSource
import dagger.Binds
import dagger.Module

/**
 * Created by trileksono on 07/12/20
 */
@Module
abstract class DataSourceModule {
    @Binds
    abstract fun bindPostRepo(repoDataSource: PostDataSource): PostRepository
}