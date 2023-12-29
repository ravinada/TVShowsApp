package com.ravinada.sps.domain.usecases

import com.ravinada.sps.data.repository.ITvShowsRepository
import com.ravinada.sps.domain.toDomainModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchTvShowUseCase @Inject constructor(
    private val repository: ITvShowsRepository
) {
    suspend operator fun invoke(query: String) = repository.searchTvShow(query).map {
        it.results.toDomainModel()
    }
}
