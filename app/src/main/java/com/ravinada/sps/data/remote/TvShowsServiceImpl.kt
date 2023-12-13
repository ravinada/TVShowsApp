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

    suspend fun getTvShowsAppDetail(
        apiKey: String,
        language: String,
        id: String
    ): TvShowsDetailResponse

    suspend fun searchTvShow(
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

    override suspend fun getTvShowsAppDetail(
        apiKey: String,
        language: String,
        id: String
    ) = getResult(
        call = { tvShowsService.getTvShowsDetail(apiKey = apiKey, language = language, id = id) },
        forceError = false
    )

    override suspend fun searchTvShow(
        query: String,
        apiKey: String,
        language: String
    ) = getResult(
        call = { tvShowsService.searchTvShow(query = query, apiKey = apiKey, language = language) },
        forceError = false
    )
}
