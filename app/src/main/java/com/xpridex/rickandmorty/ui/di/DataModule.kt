package com.xpridex.rickandmorty.ui.di

import com.xpridex.rickandmorty.data.RickAndMortyDataRepository
import com.xpridex.rickandmorty.data.remote.RickAndMortyRemoteImpl
import com.xpridex.rickandmorty.data.source.RickAndMortyRemote
import com.xpridex.rickandmorty.domain.repository.RickAndMortyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideRepository(data: RickAndMortyDataRepository): RickAndMortyRepository {
        return data
    }

    @Singleton
    @Provides
    fun provideDataSource(dataSourceRemote: RickAndMortyRemoteImpl): RickAndMortyRemote {
        return dataSourceRemote
    }
}