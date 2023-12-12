package com.ravinada.sps.usecases

import com.ravinada.sps.domain.TvShowsDomain
import com.ravinada.sps.data.repository.ITvShowsRepository
import kotlinx.coroutines.flow.map
import com.ravinada.sps.domain.toDomainModel
import javax.inject.Inject

class GetTrendingTvShowsUseCase @Inject constructor(
    private val iTvShowsRepository: ITvShowsRepository
) {
    suspend operator fun invoke(
        api_key: String,
        language: String,
    ) = iTvShowsRepository.getTrendingTvShows(api_key, language).map {
        it.results.toDomainModel()
    }
}

sealed class PopularMoviesResult {
    data class Success(val list: List<TvShowsDomain>) : PopularMoviesResult()
    data class ErrorGeneral(val error: String) : PopularMoviesResult()
    data class Loading(val isLoading: Boolean) : PopularMoviesResult()
    object InternetError : PopularMoviesResult()
    object Empty : PopularMoviesResult()
}
