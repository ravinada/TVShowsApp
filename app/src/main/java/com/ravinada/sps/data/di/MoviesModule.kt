package com.ravinada.sps.data.di

import com.ravinada.sps.data.local.FavoriteMoviesLocalDataSourceImpl
import com.ravinada.sps.data.local.IFavoriteMoviesLocalDataSource
import com.ravinada.sps.data.remote.IMoviesRemoteDataSource
import com.ravinada.sps.data.remote.IMoviesService
import com.ravinada.sps.data.remote.MoviesRemoteDataSource
import com.ravinada.sps.data.remote.MoviesService
import com.ravinada.sps.data.remote.MoviesServiceImpl
import com.ravinada.sps.data.repository.IMoviesRepository
import com.ravinada.sps.data.repository.MoviesRepository
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
        moviesServiceImpl: MoviesServiceImpl
    ): IMoviesService

    @Singleton
    @Binds
    abstract fun provideRemoteDataSource(
        remoteDataSource: MoviesRemoteDataSource
    ): IMoviesRemoteDataSource

    @Singleton
    @Binds
    abstract fun provideMoviesRepository(
        moviesRepositoryImpl: MoviesRepository
    ): IMoviesRepository


    @Singleton
    @Binds
    abstract fun provideMoviesLocalDataSource(
        moviesLocalDataSourceImpl: FavoriteMoviesLocalDataSourceImpl
    ): IFavoriteMoviesLocalDataSource

}

@Module
@InstallIn(SingletonComponent::class)
object MoviesModuleObj {

    @Singleton
    @Provides
    fun provideMoviesService(
        retrofit: Retrofit
    ): MoviesService {
        return retrofit.create(MoviesService::class.java)
    }
}
