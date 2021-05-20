package com.xpridex.rickandmorty.ui.di


import com.xpridex.rickandmorty.data.remote.RickAndMortyRestApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Reusable
    @Provides
    fun provideDogApi(retrofit: Retrofit): RickAndMortyRestApi {
        return retrofit.create(RickAndMortyRestApi::class.java)
    }
}
