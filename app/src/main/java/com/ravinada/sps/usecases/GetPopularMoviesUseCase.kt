package com.ravinada.sps.usecases

import com.ravinada.sps.domain.MovieDomain
import com.ravinada.sps.data.repository.IMoviesRepository
import kotlinx.coroutines.flow.map
import com.ravinada.sps.domain.toDomainModel
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val iMoviesRepository: IMoviesRepository
) {
    suspend operator fun invoke(
        api_key: String,
        language: String,
        page: Int
    ) = iMoviesRepository.getPopularMovies(api_key, language, page).map {
        it.results.toDomainModel()
    }
}

sealed class PopularMoviesResult {
    data class Success(val list: List<MovieDomain>) : PopularMoviesResult()
    data class ErrorGeneral(val error: String) : PopularMoviesResult()
    data class Loading(val isLoading: Boolean) : PopularMoviesResult()
    object InternetError : PopularMoviesResult()
    object Empty : PopularMoviesResult()
}
