package com.ravinada.sps.data.repository

import com.ravinada.sps.domain.PopularsMovieResponse
import com.ravinada.sps.data.local.IFavoriteMoviesLocalDataSource
import com.ravinada.sps.data.remote.IMoviesRemoteDataSource
import com.ravinada.sps.domain.MoviesDetailResponse
import com.ravinada.sps.domain.local.FavoriteMoviesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IMoviesRepository {
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


    fun getFavoriteMovies(): Flow<List<FavoriteMoviesEntity>>
    fun getFavoriteMovieById(id: Int): Flow<FavoriteMoviesEntity>
    suspend fun insertFavoriteMovie(favoriteMoviesEntity: FavoriteMoviesEntity)
    suspend fun deleteFavoriteMovie(id: Int)
}

class MoviesRepository @Inject constructor(
    private val remote: IMoviesRemoteDataSource,
    private val local: IFavoriteMoviesLocalDataSource
) : IMoviesRepository {

    override suspend fun getPopularMovies(
        apiKey: String,
        language: String,
        page: Int
    ) = remote.getPopularMovies(apiKey, language, page)

    override suspend fun getMovieDetail(
        apiKey: String,
        language: String,
        id: String
    ) = remote.getMovieDetail(apiKey, language, id)

    override suspend fun searchMovie(
        query: String,
        apiKey: String,
        language: String
    ): Flow<PopularsMovieResponse> {
        return remote.searchMovie(query, apiKey, language)
    }

    override fun getFavoriteMovies(): Flow<List<FavoriteMoviesEntity>> {
        return local.getFavoriteMovies()
    }

    override fun getFavoriteMovieById(id: Int): Flow<FavoriteMoviesEntity> {
        return local.getFavoriteMovieById(id)
    }

    override suspend fun insertFavoriteMovie(favoriteMoviesEntity: FavoriteMoviesEntity) {
        local.insertFavoriteMovie(favoriteMoviesEntity)
    }

    override suspend fun deleteFavoriteMovie(id: Int) {
        local.deleteFavoriteMovie(id)
    }
}