package com.ravinada.sps.usecases

import com.ravinada.sps.data.repository.IMoviesRepository
import com.ravinada.sps.domain.local.FavoriteMoviesEntity
import javax.inject.Inject

class InsertFavoriteMovieUseCase @Inject constructor(
    private val repository: IMoviesRepository
) {
    suspend operator fun invoke(favoriteMoviesEntity: FavoriteMoviesEntity) =
        repository.insertFavoriteMovie(favoriteMoviesEntity)
}