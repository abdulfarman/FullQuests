package com.example.quests.di

import com.example.quests.data.remote.ProductApi
import com.example.quests.data.repository.ProductRepositoryImpl
import com.example.quests.domain.repository.ProductRepository
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
    fun provideProductRepository(
        api: ProductApi
    ): ProductRepository = ProductRepositoryImpl(api)
}
