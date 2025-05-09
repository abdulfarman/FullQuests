package com.example.quests.di

import android.app.Application
import androidx.room.Room
import com.example.quests.data.local.AppDatabase
import com.example.quests.data.local.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, "app_db").build()

    @Provides
    fun provideProductDao(db: AppDatabase): ProductDao = db.productDao()
}