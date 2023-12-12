package com.ravinada.sps.data.remote

import com.ravinada.sps.domain.PopularsMovieResponse
import com.ravinada.sps.domain.MoviesDetailResponse
import com.ravinada.sps.framework.network.performNetworkFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IMoviesRemoteDataSource {
    suspend fun getPopularMovies(
        apiKey: String,
        language: String,
        page: Int
    ): Flow<PopularsMovieResponse>

    suspend fun getMovieDetail(
        apiKey: String,
        language: String,
        id: String
    ): Flow<MoviesDetailResponse>

    suspend fun searchMovie(
        query: String,
        apiKey: String,
        language: String,
    ): Flow<PopularsMovieResponse>
}


class MoviesRemoteDataSource @Inject constructor(
    private val iMoviesService: IMoviesService
) : IMoviesRemoteDataSource {

    override suspend fun getPopularMovies(
        apiKey: String,
        language: String,
        page: Int
    ) = performNetworkFlow {
        iMoviesService.getPopularMovies(apiKey, language, page)
    }

    override suspend fun getMovieDetail(
        apiKey: String,
        language: String,
        id: String
    ) = performNetworkFlow {
        iMoviesService.getMovieDetail(apiKey, language, id)
    }

    override suspend fun searchMovie(
        query: String,
        apiKey: String,
        language: String
    ) = performNetworkFlow {
        iMoviesService.searchMovie(query, apiKey, language)
    }
}
