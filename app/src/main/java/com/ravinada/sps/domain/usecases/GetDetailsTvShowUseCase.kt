package com.ravinada.sps.domain.usecases

import com.ravinada.sps.data.repository.ITvShowsRepository
import com.ravinada.sps.domain.TvShowsDetailDomain
import com.ravinada.sps.domain.toDomainModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetDetailsTvShowsUseCase @Inject constructor(
    private val iTvShowsRepository: ITvShowsRepository
) {
    suspend operator fun invoke(
        id: String
    ) = iTvShowsRepository.getTvShowDetail(id).map {
        it.toDomainModel()
    }
}

sealed class GetDetailsTvShowsResult {
    data class Loading(val isLoading: Boolean) : GetDetailsTvShowsResult()
    data class Success(val data: TvShowsDetailDomain) : GetDetailsTvShowsResult()
    data class Error(val message: String) : GetDetailsTvShowsResult()
    data class InternetError(val message: String) : GetDetailsTvShowsResult()
}

