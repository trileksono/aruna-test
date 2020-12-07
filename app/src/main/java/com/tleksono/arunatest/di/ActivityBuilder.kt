package com.tleksono.arunatest.di

import com.tleksono.arunatest.di.MainActivityProviders
import com.tleksono.arunatest.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by trileksono on 07/12/20
 */

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityProviders::class])
    abstract fun bindMainActivity(): MainActivity

}
