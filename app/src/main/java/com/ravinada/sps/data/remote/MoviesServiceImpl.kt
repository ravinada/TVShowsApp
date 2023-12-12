package com.ravinada.sps.data.remote

import com.ravinada.sps.domain.PopularsMovieResponse
import com.ravinada.sps.domain.MoviesDetailResponse
import com.ravinada.sps.framework.network.BaseDataSource
import javax.inject.Inject

interface IMoviesService {
    suspend fun getPopularMovies(
        apiKey: String,
        language: String,
        page: Int
    ): PopularsMovieResponse

    suspend fun getMovieDetail(
        apiKey: String,
        language: String,
        id: String
    ): MoviesDetailResponse

    suspend fun searchMovie(
        query: String,
        apiKey: String,
        language: String,
    ): PopularsMovieResponse
}

class MoviesServiceImpl @Inject constructor(
    private val moviesService: MoviesService
) : BaseDataSource(), IMoviesService {
    override suspend fun getPopularMovies(
        apiKey: String,
        language: String,
        page: Int
    ) = getResult(
        call = {
            moviesService.getPopularMovies(
                apiKey = apiKey,
                language = language,
                page = page
            )
        },
        forceError = false
    )

    override suspend fun getMovieDetail(
        apiKey: String,
        language: String,
        id: String
    ) = getResult(
        call = { moviesService.getMovieDetail(apiKey = apiKey, language = language, id = id) },
        forceError = false
    )

    override suspend fun searchMovie(
        query: String,
        apiKey: String,
        language: String
    ) = getResult(
        call = { moviesService.searchMovie(query = query, apiKey = apiKey, language = language) },
        forceError = false
    )
}
