package com.ravinada.sps.domain

import com.ravinada.sps.BuildConfig

data class TrendingTvShowsResponse(
    val page: Int,
    val results: List<TvShowsEntity>,
    val total_pages: Int,
    val total_results: Int
)

data class TvShowsDomain(
    val id: Int,
    val poster_path: String,
    val overview: String,
    val title: String,
    val vote_average: Float,
    val release_date: String? = null,
)

data class TvShowsEntity(
    val adult: Boolean,
    val backdrop_path: String,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Float,
    val vote_count: Int
)

fun List<TvShowsEntity>.toDomainModel(): List<TvShowsDomain> {
    return map {
        TvShowsDomain(
            id = it.id,
            poster_path = BuildConfig.IMAGE_URL + it.poster_path, // Here we are adding the base url to the poster_path
            overview = it.overview,
            title = it.name,
            vote_average = it.vote_average,
            release_date = it.first_air_date
        )
    }
}
