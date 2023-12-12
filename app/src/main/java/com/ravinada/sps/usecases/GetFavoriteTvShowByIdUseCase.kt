package com.ravinada.sps.usecases

import com.ravinada.sps.data.repository.ITvShowsRepository
import javax.inject.Inject

class GetFavoriteTvShowByIdUseCase @Inject constructor(
    private val repository: ITvShowsRepository
) {
    operator fun invoke(id: Int) = repository.getFavoriteTvShowById(id)
}