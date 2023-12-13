package com.ravinada.sps.domain

import com.ravinada.sps.BuildConfig

data class TvShowsDetailResponse(
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
    val created_by: List<CreatedBy>? = null,
    val episode_run_time: List<Any>? = null,
    val first_air_date: String? = null,
    val genres: List<Genre>? = null,
    val homepage: String? = null,
    val id: Int? = null,
    val in_production: Boolean? = null,
    val languages: List<String>? = null,
    val last_air_date: String? = null,
    val last_episode_to_air: LastEpisodeToAir? = null,
    val name: String? = null,
    val networks: List<Network>? = null,
    val next_episode_to_air: Any? = null,
    val number_of_episodes: Int? = null,
    val number_of_seasons: Int? = null,
    val origin_country: List<String>? = null,
    val original_language: String? = null,
    val original_name: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val production_companies: List<ProductionCompany>? = null,
    val production_countries: List<ProductionCountry>? = null,
    val seasons: List<Season>? = null,
    val spoken_languages: List<SpokenLanguage>? = null,
    val status: String? = null,
    val tagline: String? = null,
    val type: String? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null
)

data class Genre(
    val id: Int,
    val name: String
)

data class ProductionCompany(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)

data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)

//Domain

data class TvShowsDetailDomain(
    val id: Int? = null,
    val original_name: String? = null,
    val overview: String? = null,
    val first_air_date: String? = null,
    val poster_path: String? = null,
    val number_of_seasons: String? = null,
    val genres: List<GenreDomain>? = null,
    val backdrop_path: String? = null,
    val vote_average: Double? = null,
)

data class GenreDomain(
    val id: Int? = null,
    val name: String? = null,
)

data class CreatedBy(
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val name: String,
    val profile_path: String
)

data class LastEpisodeToAir(
    val air_date: String,
    val episode_number: Int,
    val episode_type: String,
    val id: Int,
    val name: String,
    val overview: String,
    val production_code: String,
    val runtime: Int,
    val season_number: Int,
    val show_id: Int,
    val still_path: String,
    val vote_average: Double,
    val vote_count: Int
)

data class Network(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)

data class Season(
    val air_date: String,
    val episode_count: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String,
    val season_number: Int,
    val vote_average: Double
)

fun TvShowsDetailResponse.toDomainModel(): TvShowsDetailDomain {
    return TvShowsDetailDomain(
        id = this.id,
        genres = this.genres?.toDomainGenre(),
        original_name = this.original_name,
        overview = this.overview,
        poster_path = BuildConfig.IMAGE_URL + this.poster_path,
        number_of_seasons = "${this.number_of_seasons.toString()} seasons",
        first_air_date = this.first_air_date,
        vote_average = this.vote_average,
        backdrop_path = BuildConfig.IMAGE_URL + this.backdrop_path
    )
}

fun List<Genre>.toDomainGenre(): List<GenreDomain> {
    return this.map {
        GenreDomain(
            id = it.id,
            name = it.name
        )
    }
}