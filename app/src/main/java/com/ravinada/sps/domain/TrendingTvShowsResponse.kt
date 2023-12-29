package com.ravinada.sps.domain

import com.google.gson.annotations.SerializedName
import com.ravinada.sps.BuildConfig

data class TrendingTvShowsResponse(
    val page: Int,
    val results: List<TvShowsEntity>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)

data class TvShowsDomain(
    val id: Int,
    @SerializedName("poster_path") val posterPath: String,
    val overview: String,
    val title: String,
    @SerializedName("vote_average") val voteAverage: Float,
    @SerializedName("release_date") val releaseDate: String? = null,
)

data class TvShowsEntity(
    val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("first_air_date") val firstAirDate: String,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    val id: Int,
    @SerializedName("media_type") val mediaType: String,
    val name: String,
    @SerializedName("origin_country") val originCountry: List<String>,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_name") val originalName: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("vote_average") val voteAverage: Float,
    @SerializedName("vote_count") val voteCount: Int
)

fun List<TvShowsEntity>.toDomainModel(): List<TvShowsDomain> {
    return map {
        TvShowsDomain(
            id = it.id,
            posterPath = BuildConfig.IMAGE_URL + it.posterPath, // Here we are adding the base url to the poster_path
            overview = it.overview,
            title = it.name,
            voteAverage = it.voteAverage,
            releaseDate = it.firstAirDate
        )
    }
}
