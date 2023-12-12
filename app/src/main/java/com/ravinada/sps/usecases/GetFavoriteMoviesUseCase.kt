package com.ravinada.sps.usecases

import com.ravinada.sps.data.repository.IMoviesRepository
import javax.inject.Inject

class GetFavoriteMoviesUseCase @Inject constructor(
    private val repository: IMoviesRepository
) {
    operator fun invoke() = repository.getFavoriteMovies()
}