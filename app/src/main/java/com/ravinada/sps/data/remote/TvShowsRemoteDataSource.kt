package com.ravinada.sps.data.remote

import com.ravinada.sps.domain.TrendingTvShowsResponse
import com.ravinada.sps.domain.TvShowsDetailResponse
import com.ravinada.sps.framework.network.performNetworkFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ITvShowsRemoteDataSource {
    suspend fun getTrendingTvShows(
        apiKey: String,
        language: String
    ): Flow<TrendingTvShowsResponse>

    suspend fun getTvShowDetail(
        apiKey: String,
        language: String,
        id: String
    ): Flow<TvShowsDetailResponse>

    suspend fun searchTvShow(
        query: String,
        apiKey: String,
        language: String,
    ): Flow<TrendingTvShowsResponse>
}

class TvShowsRemoteDataSource @Inject constructor(
    private val iTvShowsService: ITvShowsService
) : ITvShowsRemoteDataSource {

    override suspend fun getTrendingTvShows(
        apiKey: String,
        language: String
    ) = performNetworkFlow {
        iTvShowsService.getTrendingTvShows(apiKey, language)
    }

    override suspend fun getTvShowDetail(
        apiKey: String,
        language: String,
        id: String
    ) = performNetworkFlow {
        iTvShowsService.getTvShowsAppDetail(apiKey, language, id)
    }

    override suspend fun searchTvShow(
        query: String,
        apiKey: String,
        language: String
    ) = performNetworkFlow {
        iTvShowsService.searchTvShow(query, apiKey, language)
    }
}
