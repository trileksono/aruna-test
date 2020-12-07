package com.tleksono.arunatest.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tleksono.arunatest.di.annotation.ViewModelKey
import com.tleksono.arunatest.view.mainfragment.MainFragmentViewModel
//import com.tleksono.arunatest.view.mainfragment.MainFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBuilderModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactorys): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    abstract fun bindMainViewModel(mainFragmentViewModel: MainFragmentViewModel): ViewModel
}
