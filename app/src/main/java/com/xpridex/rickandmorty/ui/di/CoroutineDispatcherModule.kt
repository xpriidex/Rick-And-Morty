package com.xpridex.rickandmorty.ui.di


import com.xpridex.rickandmorty.core.execution.AppCoroutineExecutionThread
import com.xpridex.rickandmorty.core.execution.CoroutineExecutionThread
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class CoroutineDispatcherModule {

    @Reusable
    @Provides
    fun provideCoroutineDispatchers(): CoroutineExecutionThread {
        return AppCoroutineExecutionThread()
    }
}
