package com.ravinada.sps.data.remote

import com.ravinada.sps.domain.TrendingTvShowsResponse
import com.ravinada.sps.domain.TvShowsDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowsService {
    @GET("trending/tv/day")
    suspend fun getTrendingTvShows(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
    ): Response<TrendingTvShowsResponse>

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        ): Response<TvShowsDetailResponse>

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
    ): Response<TrendingTvShowsResponse>
}