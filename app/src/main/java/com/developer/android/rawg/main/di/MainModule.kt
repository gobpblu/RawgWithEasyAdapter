package com.developer.android.rawg.main.di

import com.developer.android.rawg.main.repository.MainRemoteRepository
import com.developer.android.rawg.main.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MainModule {

    @Binds
    @Singleton
    abstract fun bindsMainRepository(
        mainRemoteRepository: MainRemoteRepository
    ): MainRepository

}