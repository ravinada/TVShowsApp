package com.ravinada.sps.domain.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteTvShowsEntityDao {

    @Query("SELECT * FROM favorite_tvShows")
    fun getAll(): Flow<List<FavoriteTvShowsEntity>>

    @Query("SELECT * FROM favorite_tvShows WHERE id = :id")
    fun getById(id: Int): Flow<FavoriteTvShowsEntity>

    @Query("DELETE FROM favorite_tvShows WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteTvShowsEntity: FavoriteTvShowsEntity)
}
