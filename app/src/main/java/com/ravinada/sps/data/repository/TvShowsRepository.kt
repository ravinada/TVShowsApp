package com.ravinada.sps.data.repository

import com.ravinada.sps.data.local.IFavoriteTvShowsLocalDataSource
import com.ravinada.sps.data.remote.ITvShowsRemoteDataSource
import com.ravinada.sps.domain.TrendingTvShowsResponse
import com.ravinada.sps.domain.TvShowsDetailResponse
import com.ravinada.sps.domain.local.FavoriteTvShowsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ITvShowsRepository {
    suspend fun getTrendingTvShows(): Flow<TrendingTvShowsResponse>

    suspend fun getTvShowDetail(id: String): Flow<TvShowsDetailResponse>

    suspend fun searchTvShow(query: String): Flow<TrendingTvShowsResponse>

    suspend fun getSimilarTvShows(id: String): Flow<TrendingTvShowsResponse>

    fun getFavoriteTvShows(): Flow<List<FavoriteTvShowsEntity>>

    fun getFavoriteTvShowById(id: Int): Flow<FavoriteTvShowsEntity>

    suspend fun insertFavoriteTvShow(favoriteTvShowsEntity: FavoriteTvShowsEntity)

    suspend fun deleteFavoriteTvShow(id: Int)
}

class TvShowsRepository @Inject constructor(
    private val remote: ITvShowsRemoteDataSource,
    private val local: IFavoriteTvShowsLocalDataSource
) : ITvShowsRepository {

    override suspend fun getTrendingTvShows() = remote.getTrendingTvShows()

    override suspend fun getTvShowDetail(id: String) = remote.getTvShowDetail(id)

    override suspend fun searchTvShow(query: String): Flow<TrendingTvShowsResponse> {
        return remote.searchTvShow(query)
    }

    override suspend fun getSimilarTvShows(id: String) = remote.getSimilarTvShows(id)

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