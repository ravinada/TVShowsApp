package com.ravinada.sps.data.di

import com.ravinada.sps.data.local.FavoriteTvShowsLocalDataSourceImpl
import com.ravinada.sps.data.local.IFavoriteTvShowsLocalDataSource
import com.ravinada.sps.data.remote.ITvShowsRemoteDataSource
import com.ravinada.sps.data.remote.ITvShowsService
import com.ravinada.sps.data.remote.TvShowsRemoteDataSource
import com.ravinada.sps.data.remote.TvShowsService
import com.ravinada.sps.data.remote.TvShowsServiceImpl
import com.ravinada.sps.data.repository.ITvShowsRepository
import com.ravinada.sps.data.repository.TvShowsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MoviesModule {

    @Singleton
    @Binds
    abstract fun provideMovieServices(
        moviesServiceImpl: TvShowsServiceImpl
    ): ITvShowsService

    @Singleton
    @Binds
    abstract fun provideRemoteDataSource(
        remoteDataSource: TvShowsRemoteDataSource
    ): ITvShowsRemoteDataSource

    @Singleton
    @Binds
    abstract fun provideMoviesRepository(
        tvShowsRepositoryImpl: TvShowsRepository
    ): ITvShowsRepository


    @Singleton
    @Binds
    abstract fun provideMoviesLocalDataSource(
        moviesLocalDataSourceImpl: FavoriteTvShowsLocalDataSourceImpl
    ): IFavoriteTvShowsLocalDataSource

}

@Module
@InstallIn(SingletonComponent::class)
object MoviesModuleObj {

    @Singleton
    @Provides
    fun provideMoviesService(
        retrofit: Retrofit
    ): TvShowsService {
        return retrofit.create(TvShowsService::class.java)
    }
}
