package com.ravinada.sps.usecases

import com.ravinada.sps.data.repository.ITvShowsRepository
import com.ravinada.sps.domain.MovieDetailDomain
import com.ravinada.sps.domain.toDomainModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetDetailsMovieUseCase @Inject constructor(
    private val iTvShowsRepository: ITvShowsRepository
) {
    suspend operator fun invoke(
        api_key: String,
        language: String,
        id: String
    ) = iTvShowsRepository.getTvShowDetail(api_key, language, id).map {
        it.toDomainModel()
    }
}

sealed class GetDetailsMovieResult {
    data class Loading(val isLoading: Boolean) : GetDetailsMovieResult()
    data class Success(val data: MovieDetailDomain) : GetDetailsMovieResult()
    data class Error(val message: String) : GetDetailsMovieResult()
    data class InternetError(val message: String) : GetDetailsMovieResult()
}

