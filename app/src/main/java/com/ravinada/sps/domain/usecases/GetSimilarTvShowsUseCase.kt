package com.ravinada.sps.domain.usecases

import com.ravinada.sps.data.repository.ITvShowsRepository
import com.ravinada.sps.domain.TvShowsDomain
import com.ravinada.sps.domain.toDomainModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSimilarTvShowsUseCase @Inject constructor(
    private val iTvShowsRepository: ITvShowsRepository
) {
    suspend operator fun invoke(id: String) = iTvShowsRepository.getSimilarTvShows(id).map {
        it.results.toDomainModel()
    }
}

sealed class SimilarTvShowsResult {
    data class Success(val list: List<TvShowsDomain>) : SimilarTvShowsResult()
    data class Loading(val isLoading: Boolean) : SimilarTvShowsResult()
    data class Error(val message: String) : SimilarTvShowsResult()
}
