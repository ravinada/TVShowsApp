package com.ravinada.sps.usecases

import com.ravinada.sps.data.repository.ITvShowsRepository
import com.ravinada.sps.domain.toDomainModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchTvShowUseCase @Inject constructor(
    private val repository: ITvShowsRepository
) {
    suspend operator fun invoke(
        query: String,
        api_key: String,
        language: String
    ) = repository.searchTvShow(query, api_key, language).map {
        it.results.toDomainModel()
    }
}
