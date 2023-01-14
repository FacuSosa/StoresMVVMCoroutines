package com.curso.storesmvvmcoroutines.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.curso.storesmvvmcoroutines.common.entities.StoreEntity

@Database(entities = [StoreEntity::class], version = 3)
abstract class StoreDatabase : RoomDatabase() {
    abstract fun storeDao(): StoreDao
}