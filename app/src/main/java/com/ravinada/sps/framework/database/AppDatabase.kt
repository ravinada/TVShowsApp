package com.ravinada.sps.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ravinada.sps.domain.local.FavoriteTvShowsEntity
import com.ravinada.sps.domain.local.FavoriteTvShowsEntityDao

@Database(entities = [FavoriteTvShowsEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteMoviesDao(): FavoriteTvShowsEntityDao
}