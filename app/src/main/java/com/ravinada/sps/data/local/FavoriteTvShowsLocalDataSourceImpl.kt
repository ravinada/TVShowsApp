package com.ravinada.sps.data.local

import com.ravinada.sps.domain.local.FavoriteTvShowsEntity
import com.ravinada.sps.domain.local.FavoriteTvShowsEntityDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IFavoriteTvShowsLocalDataSource {
    fun getFavoriteTvShows(): Flow<List<FavoriteTvShowsEntity>>
    fun getFavoriteTvShowById(id: Int): Flow<FavoriteTvShowsEntity>
    suspend fun insertFavoriteTvShow(favoriteTvShowsEntity: FavoriteTvShowsEntity)
    suspend fun deleteFavoriteTvShow(id: Int)
}

class FavoriteTvShowsLocalDataSourceImpl @Inject constructor(
    private val tvShowsDao: FavoriteTvShowsEntityDao
) : IFavoriteTvShowsLocalDataSource {
    override fun getFavoriteTvShows(): Flow<List<FavoriteTvShowsEntity>> {
        return tvShowsDao.getAll()
    }

    override fun getFavoriteTvShowById(id: Int): Flow<FavoriteTvShowsEntity> {
        return tvShowsDao.getById(id)
    }

    override suspend fun insertFavoriteTvShow(favoriteTvShowsEntity: FavoriteTvShowsEntity) {
        return tvShowsDao.insert(favoriteTvShowsEntity)
    }

    override suspend fun deleteFavoriteTvShow(id: Int) {
        return tvShowsDao.deleteById(id)
    }

}