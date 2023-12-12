package com.ravinada.sps.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ravinada.sps.domain.local.FavoriteMoviesEntity
import com.ravinada.sps.domain.local.FavoriteMoviesEntityDao

@Database(entities = [FavoriteMoviesEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteMoviesDao(): FavoriteMoviesEntityDao
}