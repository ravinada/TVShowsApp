package com.ravinada.sps.data.remote

import com.ravinada.sps.domain.TrendingTvShowsResponse
import com.ravinada.sps.domain.TvShowsDetailResponse
import com.ravinada.sps.framework.network.BaseDataSource
import javax.inject.Inject

interface ITvShowsService {
    suspend fun getTrendingTvShows(
        apiKey: String,
        language: String
    ): TrendingTvShowsResponse

    suspend fun getMovieDetail(
        apiKey: String,
        language: String,
        id: String
    ): TvShowsDetailResponse

    suspend fun searchMovie(
        query: String,
        apiKey: String,
        language: String,
    ): TrendingTvShowsResponse
}

class TvShowsServiceImpl @Inject constructor(
    private val tvShowsService: TvShowsService
) : BaseDataSource(), ITvShowsService {
    override suspend fun getTrendingTvShows(
        apiKey: String,
        language: String
    ) = getResult(
        call = {
            tvShowsService.getTrendingTvShows(
                apiKey = apiKey,
                language = language
            )
        },
        forceError = false
    )

    override suspend fun getMovieDetail(
        apiKey: String,
        language: String,
        id: String
    ) = getResult(
        call = { tvShowsService.getMovieDetail(apiKey = apiKey, language = language, id = id) },
        forceError = false
    )

    override suspend fun searchMovie(
        query: String,
        apiKey: String,
        language: String
    ) = getResult(
        call = { tvShowsService.searchMovie(query = query, apiKey = apiKey, language = language) },
        forceError = false
    )
}
