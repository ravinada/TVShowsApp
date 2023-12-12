package com.ravinada.sps.usecases

import com.ravinada.sps.data.repository.ITvShowsRepository
import javax.inject.Inject

class DeleteFavoriteTvShowUseCase @Inject constructor(
    private val repository: ITvShowsRepository
) {
    suspend operator fun invoke(id: Int) = repository.deleteFavoriteTvShow(id)
}