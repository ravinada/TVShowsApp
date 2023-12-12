package com.ravinada.sps.data.repository

import com.ravinada.sps.domain.TrendingTvShowsResponse
import com.ravinada.sps.data.local.IFavoriteTvShowsLocalDataSource
import com.ravinada.sps.data.remote.ITvShowsRemoteDataSource
import com.ravinada.sps.domain.TvShowsDetailResponse
import com.ravinada.sps.domain.local.FavoriteTvShowsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ITvShowsRepository {
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

    fun getFavoriteTvShows(): Flow<List<FavoriteTvShowsEntity>>
    fun getFavoriteTvShowById(id: Int): Flow<FavoriteTvShowsEntity>
    suspend fun insertFavoriteTvShow(favoriteTvShowsEntity: FavoriteTvShowsEntity)
    suspend fun deleteFavoriteTvShow(id: Int)
}

class TvShowsRepository @Inject constructor(
    private val remote: ITvShowsRemoteDataSource,
    private val local: IFavoriteTvShowsLocalDataSource
) : ITvShowsRepository {

    override suspend fun getTrendingTvShows(
        apiKey: String,
        language: String
    ) = remote.getTrendingTvShows(apiKey, language)

    override suspend fun getTvShowDetail(
        apiKey: String,
        language: String,
        id: String
    ) = remote.getTvShowDetail(apiKey, language, id)

    override suspend fun searchTvShow(
        query: String,
        apiKey: String,
        language: String
    ): Flow<TrendingTvShowsResponse> {
        return remote.searchTvShow(query, apiKey, language)
    }

    override fun getFavoriteTvShows(): Flow<List<FavoriteTvShowsEntity>> {
        return local.getFavoriteTvShows()
    }

    override fun getFavoriteTvShowById(id: Int): Flow<FavoriteTvShowsEntity> {
        return local.getFavoriteTvShowById(id)
    }

    override suspend fun insertFavoriteTvShow(favoriteTvShowsEntity: FavoriteTvShowsEntity) {
        local.insertFavoriteTvShow(favoriteTvShowsEntity)
    }

    override suspend fun deleteFavoriteTvShow(id: Int) {
        local.deleteFavoriteTvShow(id)
    }
}