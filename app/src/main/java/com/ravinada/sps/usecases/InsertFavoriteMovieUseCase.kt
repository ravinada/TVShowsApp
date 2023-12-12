package com.ravinada.sps.usecases

import com.ravinada.sps.data.repository.ITvShowsRepository
import com.ravinada.sps.domain.local.FavoriteTvShowsEntity
import javax.inject.Inject

class InsertFavoriteMovieUseCase @Inject constructor(
    private val repository: ITvShowsRepository
) {
    suspend operator fun invoke(favoriteTvShowsEntity: FavoriteTvShowsEntity) =
        repository.insertFavoriteTvShow(favoriteTvShowsEntity)
}