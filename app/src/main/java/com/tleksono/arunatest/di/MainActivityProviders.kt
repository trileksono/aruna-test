package com.tleksono.arunatest.di

import com.tleksono.arunatest.view.mainfragment.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by trileksono on 07/12/20
 */

@Module
abstract class MainActivityProviders {

    @ContributesAndroidInjector
    abstract fun provideMainFragment(): MainFragment

}
