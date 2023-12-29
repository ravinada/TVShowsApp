package com.ravinada.sps.usecases.data

import com.ravinada.sps.data.repository.ITvShowsRepository
import com.ravinada.sps.domain.TrendingTvShowsResponse
import com.ravinada.sps.domain.TvShowsDetailResponse
import com.ravinada.sps.domain.TvShowsEntity
import com.ravinada.sps.domain.local.FavoriteTvShowsEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.seconds

class FakeRepositorySuccessApi : ITvShowsRepository {
    override suspend fun getTrendingTvShows(): Flow<TrendingTvShowsResponse> {
        return flow {
            delay(2.seconds) // Simulate network delay
            emit(trendingTvShowsFakeResponse())
        }
    }

    override suspend fun getTvShowDetail(id: String): Flow<TvShowsDetailResponse> {
        return flow {
            emit(
                TvShowsDetailResponse(
                    adult = false,
                    backdropPath = "",
                    createdBy = listOf(),
                    episodeRunTime = listOf(),
                    firstAirDate = "",
                    genres = listOf(),
                    homepage = "",
                    id = 1,
                    inProduction = false,
                    languages = listOf(),
                    lastAirDate = "",
                    lastEpisodeToAir = null,
                    name = "",
                    networks = listOf(),
                    nextEpisodeToAir = "",
                    numberOfEpisodes = 0,
                    numberOfSeasons = 0,
                    originCountry = listOf(),
                    originalLanguage = "",
                    originalName = "",
                    overview = "",
                    popularity = 0.0,
                    posterPath = "",
                    productionCompanies = listOf(),
                    productionCountries = listOf(),
                    seasons = listOf(),
                    spokenLanguages = listOf(),
                    status = "",
                    tagline = "",
                    type = "",
                    voteAverage = 0.0,
                    voteCount = 0
                )
            )
        }
    }

    override suspend fun searchTvShow(query: String): Flow<TrendingTvShowsResponse> {
        return flow {
            emit(
                TrendingTvShowsResponse(
                    page = 1,
                    results = listOf(),
                    totalPages = 1,
                    totalResults = 1
                )
            )
        }
    }

    override suspend fun getSimilarTvShows(id: String): Flow<TrendingTvShowsResponse> {
        TODO("Not yet implemented")
    }

    override fun getFavoriteTvShows(): Flow<List<FavoriteTvShowsEntity>> {
        return flow {
            emit(
                listOf(
                    FavoriteTvShowsEntity(
                        id = 1,
                        title = "",
                        overview = "",
                        poster_path = "",
                        vote_average = 1.0f,
                        release_date = ""
                    )
                )
            )
        }
    }

    override fun getFavoriteTvShowById(id: Int): Flow<FavoriteTvShowsEntity> {
        return flow {
            emit(
                FavoriteTvShowsEntity(
                    id = 1,
                    title = "",
                    overview = "",
                    poster_path = "",
                    vote_average = 1.0f,
                    release_date = ""
                )
            )
        }
    }

    override suspend fun insertFavoriteTvShow(favoriteTvShowsEntity: FavoriteTvShowsEntity) {
        // nothing to do
    }

    override suspend fun deleteFavoriteTvShow(id: Int) {
        // nothing to do
    }

    private fun trendingTvShowsFakeResponse() = TrendingTvShowsResponse(
        page = 1,
        results = listOf(
            TvShowsEntity(
                adult = false,
                backdropPath = "",
                firstAirDate = "",
                genreIds = listOf(),
                id = 1,
                mediaType = "",
                name = "",
                originCountry = listOf(),
                originalLanguage = "",
                originalName = "",
                overview = "",
                popularity = 0.0,
                posterPath = "",
                voteAverage = 0.0f,
                voteCount = 0
            )
        ),
        totalPages = 1,
        totalResults = 1
    )
}