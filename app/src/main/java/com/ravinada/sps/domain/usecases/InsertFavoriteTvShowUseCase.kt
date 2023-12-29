package com.ravinada.sps.domain.usecases

import com.ravinada.sps.data.repository.ITvShowsRepository
import com.ravinada.sps.domain.local.FavoriteTvShowsEntity
import javax.inject.Inject

class InsertFavoriteTvShowUseCase @Inject constructor(
    private val repository: ITvShowsRepository
) {
    suspend operator fun invoke(favoriteTvShowsEntity: FavoriteTvShowsEntity) =
        repository.insertFavoriteTvShow(favoriteTvShowsEntity)
}