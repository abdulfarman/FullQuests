package com.example.quests.di

import com.example.quests.domain.repository.PostRepository
import com.example.quests.domain.usecase.FetchPostByIdUseCase
import com.example.quests.domain.usecase.FetchPostListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseProvider {

    @Provides
    fun provideFetchPostListUseCase(
        repository: PostRepository
    ): FetchPostListUseCase = FetchPostListUseCase(repository)

    @Provides
    fun provideFetchPostByIdUseCase(
        repository: PostRepository
    ): FetchPostByIdUseCase = FetchPostByIdUseCase(repository)

}