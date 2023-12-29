package com.ravinada.sps.data.remote

import com.ravinada.sps.domain.TrendingTvShowsResponse
import com.ravinada.sps.domain.TvShowsDetailResponse
import com.ravinada.sps.framework.network.performNetworkFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ITvShowsRemoteDataSource {
    suspend fun getTrendingTvShows(): Flow<TrendingTvShowsResponse>

    suspend fun getTvShowDetail(id: String): Flow<TvShowsDetailResponse>

    suspend fun searchTvShow(query: String): Flow<TrendingTvShowsResponse>
}

class TvShowsRemoteDataSource @Inject constructor(
    private val iTvShowsService: ITvShowsService
) : ITvShowsRemoteDataSource {

    override suspend fun getTrendingTvShows() = performNetworkFlow {
        iTvShowsService.getTrendingTvShows()
    }

    override suspend fun getTvShowDetail(id: String) = performNetworkFlow {
        iTvShowsService.getTvShowsAppDetail(id)
    }

    override suspend fun searchTvShow(query: String) = performNetworkFlow {
        iTvShowsService.searchTvShow(query)
    }
}
