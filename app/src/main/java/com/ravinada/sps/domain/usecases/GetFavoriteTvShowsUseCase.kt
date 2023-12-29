package com.ravinada.sps.domain.usecases

import com.ravinada.sps.data.repository.ITvShowsRepository
import javax.inject.Inject

class GetFavoriteTvShowsUseCase @Inject constructor(
    private val repository: ITvShowsRepository
) {
    operator fun invoke() = repository.getFavoriteTvShows()
}