package com.ravinada.sps.domain.usecases

import com.ravinada.sps.data.repository.ITvShowsRepository
import com.ravinada.sps.domain.TvShowsDomain
import com.ravinada.sps.domain.toDomainModel
import kotlinx.coroutines.flow.map
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

sealed class PopularTvShowsResult {
    data class Success(val list: List<TvShowsDomain>) : PopularTvShowsResult()
    data class ErrorGeneral(val error: String) : PopularTvShowsResult()
    data class Loading(val isLoading: Boolean) : PopularTvShowsResult()
    object InternetError : PopularTvShowsResult()
    object Empty : PopularTvShowsResult()
}
