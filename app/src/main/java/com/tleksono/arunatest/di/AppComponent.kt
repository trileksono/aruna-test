package com.tleksono.arunatest.di

import android.app.Application
import com.tleksono.arunatest.Apps
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by trileksono on 07/12/20
 */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class,
        AppModule::class,
        ViewModelBuilderModule::class,
        DataSourceModule::class
    ]
)
interface AppComponent : AndroidInjector<Apps> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}