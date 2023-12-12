package com.ravinada.sps.usecases

import com.ravinada.sps.data.repository.IMoviesRepository
import javax.inject.Inject

class GetFavoriteMovieByIdUseCase @Inject constructor(
    private val repository: IMoviesRepository
) {
    operator fun invoke(id: Int) = repository.getFavoriteMovieById(id)
}