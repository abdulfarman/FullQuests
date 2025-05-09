package com.example.quests.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quests.data.model.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
