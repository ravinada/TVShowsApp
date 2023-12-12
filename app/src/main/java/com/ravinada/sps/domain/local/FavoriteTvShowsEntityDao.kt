package com.ravinada.sps.domain.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteTvShowsEntityDao {

    @Query("SELECT * FROM favorite_movies")
    fun getAll(): Flow<List<FavoriteTvShowsEntity>>

    @Query("SELECT * FROM favorite_movies WHERE id = :id")
    fun getById(id: Int): Flow<FavoriteTvShowsEntity>

    @Query("DELETE FROM favorite_movies WHERE id = :id")
    suspend fun deleteById(id: Int)

    //add
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteTvShowsEntity: FavoriteTvShowsEntity)
}