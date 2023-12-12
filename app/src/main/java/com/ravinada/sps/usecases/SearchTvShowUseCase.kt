package com.ravinada.sps.usecases

import com.ravinada.sps.data.repository.ITvShowsRepository
import kotlinx.coroutines.flow.map
import com.ravinada.sps.domain.toDomainModel
import javax.inject.Inject

class SearchTvShowUseCase @Inject constructor(
    private val moviesRepository: ITvShowsRepository
) {
    suspend operator fun invoke(
        query: String,
        api_key: String,
        language: String
    ) = moviesRepository.searchTvShow(query, api_key, language).map {
        it.results.toDomainModel()
    }
}
