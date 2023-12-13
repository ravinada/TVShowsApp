package com.ravinada.sps.domain.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorite_tvShows")
data class FavoriteTvShowsEntity(
    @PrimaryKey val id: Int,
    val poster_path: String,
    val overview: String,
    val title: String,
    val vote_average: Float,
    val release_date: String,
)