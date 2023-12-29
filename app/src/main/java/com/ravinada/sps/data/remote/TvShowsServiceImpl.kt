package com.ravinada.sps.data.remote

import com.ravinada.sps.BuildConfig
import com.ravinada.sps.domain.TrendingTvShowsResponse
import com.ravinada.sps.domain.TvShowsDetailResponse
import com.ravinada.sps.framework.network.BaseDataSource
import javax.inject.Inject

interface ITvShowsService {
    suspend fun getTrendingTvShows(): TrendingTvShowsResponse

    suspend fun getTvShowsAppDetail(id: String): TvShowsDetailResponse

    suspend fun searchTvShow(query: String): TrendingTvShowsResponse

    suspend fun getSimilarTvShows(id: String): TrendingTvShowsResponse
}

class TvShowsServiceImpl @Inject constructor(
    private val tvShowsService: TvShowsService
) : BaseDataSource(), ITvShowsService {
    override suspend fun getTrendingTvShows() = getResult(
        call = {
            tvShowsService.getTrendingTvShows(
                apiKey = BuildConfig.API_KEY,
                language = "en-US"
            )
        },
        forceError = false
    )

    override suspend fun getTvShowsAppDetail(id: String) = getResult(
        call = {
            tvShowsService.getTvShowsDetail(
                apiKey = BuildConfig.API_KEY,
                language = "en-US",
                id = id
            )
        },
        forceError = false
    )

    override suspend fun searchTvShow(query: String) = getResult(
        call = {
            tvShowsService.searchTvShow(
                query = query,
                apiKey = BuildConfig.API_KEY,
                language = "en-US"
            )
        },
        forceError = false
    )

    override suspend fun getSimilarTvShows(id: String) = getResult(
        call = {
            tvShowsService.getSimilarTvShows(
                apiKey = BuildConfig.API_KEY,
                language = "en-US",
                id = id
            )
        },
        forceError = false
    )
}
