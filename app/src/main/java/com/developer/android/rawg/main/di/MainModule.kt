package com.developer.android.rawg.main.di

import com.developer.android.rawg.main.repository.MainLocalRepository
import com.developer.android.rawg.main.repository.MainLocalRepositoryImpl
import com.developer.android.rawg.main.repository.MainRemoteRemoteRepositoryImpl
import com.developer.android.rawg.main.repository.MainRemoteRepository
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
    abstract fun bindsMainRemoteRepository(
        mainRemoteRepositoryImpl: MainRemoteRemoteRepositoryImpl
    ): MainRemoteRepository

    @Binds
    @Singleton
    abstract fun bindsMainLocalRepository(
        mainLocalRepository: MainLocalRepositoryImpl
    ): MainLocalRepository

}