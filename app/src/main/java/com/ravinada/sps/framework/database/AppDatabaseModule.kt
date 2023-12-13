package com.ravinada.sps.framework.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {

    @Singleton
    @Provides
    fun provideFavoriteTvShowsDao(appDatabase: AppDatabase) = appDatabase.favoriteTvSHowsDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context) = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java,
        "TvShowsApp"
    ).fallbackToDestructiveMigration().build()
}
