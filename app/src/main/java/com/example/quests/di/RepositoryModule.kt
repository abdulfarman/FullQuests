package com.example.quests.di

import com.example.quests.data.remote.PostApi
import com.example.quests.data.repository.PostRepositoryImpl
import com.example.quests.domain.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryProvider {

    @Provides
    @Singleton
    fun providePostRepository(
        api: PostApi
    ): PostRepository = PostRepositoryImpl(api)
}
