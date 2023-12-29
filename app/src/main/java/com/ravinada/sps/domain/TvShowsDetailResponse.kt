package com.ravinada.sps.domain

import com.google.gson.annotations.SerializedName
import com.ravinada.sps.BuildConfig

data class TvShowsDetailResponse(
    @SerializedName("adult") val adult: Boolean? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    val createdBy: List<CreatedBy>? = null,
    @SerializedName("episode_run_time") val episodeRunTime: List<Any>? = null,
    @SerializedName("first_air_date") val firstAirDate: String? = null,
    val genres: List<Genre>? = null,
    val homepage: String? = null,
    val id: Int? = null,
    @SerializedName("in_production") val inProduction: Boolean? = null,
    val languages: List<String>? = null,
    @SerializedName("last_air_date") val lastAirDate: String? = null,
    @SerializedName("last_episode_to_air") val lastEpisodeToAir: LastEpisodeToAir? = null,
    val name: String? = null,
    val networks: List<Network>? = null,
    @SerializedName("next_episode_to_air") val nextEpisodeToAir: Any? = null,
    @SerializedName("number_of_episodes") val numberOfEpisodes: Int? = null,
    @SerializedName("number_of_seasons") val numberOfSeasons: Int? = null,
    @SerializedName("origin_country") val originCountry: List<String>? = null,
    @SerializedName("original_language") val originalLanguage: String? = null,
    @SerializedName("original_name") val originalName: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("production_companies") val productionCompanies: List<ProductionCompany>? = null,
    @SerializedName("production_countries") val productionCountries: List<ProductionCountry>? = null,
    val seasons: List<Season>? = null,
    @SerializedName("spoken_languages") val spokenLanguages: List<SpokenLanguage>? = null,
    val status: String? = null,
    val tagline: String? = null,
    val type: String? = null,
    @SerializedName("vote_average") val voteAverage: Double? = null,
    @SerializedName("vote_count") val voteCount: Int? = null
)

data class Genre(
    val id: Int,
    val name: String
)

data class ProductionCompany(
    val id: Int,
    @SerializedName("logo_path") val logoPath: String,
    val name: String,
    @SerializedName("origin_country") val originCountry: String
)

data class ProductionCountry(
    @SerializedName("iso_3166_1") val iso31661: String,
    val name: String
)

data class SpokenLanguage(
    @SerializedName("english_name") val englishName: String,
    @SerializedName("iso_639_1") val iso6391: String,
    val name: String
)

data class TvShowsDetailDomain(
    val id: Int? = null,
    @SerializedName("original_name") val originalName: String? = null,
    val overview: String? = null,
    @SerializedName("first_air_date") val firstAirDate: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("number_of_seasons") val numberOfSeasons: String? = null,
    val genres: List<GenreDomain>? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("vote_average") val voteAverage: Double? = null,
)

data class GenreDomain(
    val id: Int? = null,
    val name: String? = null,
)

data class CreatedBy(
    @SerializedName("credit_id") val creditId: String,
    val gender: Int,
    val id: Int,
    val name: String,
    @SerializedName("profile_path") val profilePath: String
)

data class LastEpisodeToAir(
    @SerializedName("air_date") val airDate: String,
    @SerializedName("episode_number") val episodeNumber: Int,
    @SerializedName("episode_type") val episodeType: String,
    val id: Int,
    val name: String,
    val overview: String,
    @SerializedName("production_code") val productionCode: String,
    val runtime: Int,
    @SerializedName("season_number") val seasonNumber: Int,
    @SerializedName("show_id") val showId: Int,
    @SerializedName("still_path") val stillPath: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
)

data class Network(
    val id: Int,
    @SerializedName("logo_path")

    val logoPath: String,
    val name: String,
    @SerializedName("origin_country") val originCountry: String
)

data class Season(
    @SerializedName("air_date") val airDate: String,
    @SerializedName("episode_count") val episodeCount: Int,
    val id: Int,
    val name: String,
    val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("season_number") val seasonNumber: Int,
    @SerializedName("vote_average") val voteAverage: Double
)

fun TvShowsDetailResponse.toDomainModel(): TvShowsDetailDomain {
    return TvShowsDetailDomain(
        id = this.id,
        genres = this.genres?.toDomainGenre(),
        originalName = this.originalName,
        overview = this.overview,
        posterPath = BuildConfig.IMAGE_URL + this.posterPath,
        numberOfSeasons = "${this.numberOfSeasons.toString()} seasons",
        firstAirDate = this.firstAirDate,
        voteAverage = this.voteAverage,
        backdropPath = BuildConfig.IMAGE_URL + this.backdropPath
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
