package com.ravinada.sps.usecases

import com.ravinada.sps.data.repository.IMoviesRepository
import kotlinx.coroutines.flow.map
import com.ravinada.sps.domain.toDomainModel
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val moviesRepository: IMoviesRepository
) {
    suspend operator fun invoke(
        query: String,
        api_key: String,
        language: String
    ) = moviesRepository.searchMovie(query, api_key, language).map {
        it.results.toDomainModel()
    }
}
