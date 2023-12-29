package com.ravinada.sps.usecases.data

import com.ravinada.sps.data.repository.ITvShowsRepository
import com.ravinada.sps.domain.TrendingTvShowsResponse
import com.ravinada.sps.domain.TvShowsDetailResponse
import com.ravinada.sps.domain.local.FavoriteTvShowsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException

class FakeRepositoryErrorApi : ITvShowsRepository {
    override suspend fun getTrendingTvShows(): Flow<TrendingTvShowsResponse> {
        return flow {
            throw HttpException(
                retrofit2.Response.error<TrendingTvShowsResponse>(
                    500,
                    "Error".toResponseBody("application/json".toMediaTypeOrNull())
                )
            )
        }
    }

    override suspend fun getTvShowDetail(id: String): Flow<TvShowsDetailResponse> {
        return flow {
            throw HttpException(
                retrofit2.Response.error<TvShowsDetailResponse>(
                    500,
                    "Error".toResponseBody("application/json".toMediaTypeOrNull())
                )
            )
        }
    }

    override suspend fun searchTvShow(query: String): Flow<TrendingTvShowsResponse> {
        return flow {
            throw HttpException(
                retrofit2.Response.error<TrendingTvShowsResponse>(
                    500,
                    "Error".toResponseBody("application/json".toMediaTypeOrNull())
                )
            )
        }
    }

    override suspend fun getSimilarTvShows(id: String): Flow<TrendingTvShowsResponse> {
        return flow {
            throw HttpException(
                retrofit2.Response.error<TrendingTvShowsResponse>(
                    500,
                    "Error".toResponseBody("application/json".toMediaTypeOrNull())
                )
            )
        }
    }

    override fun getFavoriteTvShows(): Flow<List<FavoriteTvShowsEntity>> {
        return flow {
            emit(emptyList())
        }
    }

    override fun getFavoriteTvShowById(id: Int): Flow<FavoriteTvShowsEntity> {
        return flow {
            throw HttpException(
                retrofit2.Response.error<TvShowsDetailResponse>(
                    500,
                    "Error".toResponseBody("application/json".toMediaTypeOrNull())
                )
            )
        }
    }

    override suspend fun insertFavoriteTvShow(favoriteTvShowsEntity: FavoriteTvShowsEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavoriteTvShow(id: Int) {
        TODO("Not yet implemented")
    }

}