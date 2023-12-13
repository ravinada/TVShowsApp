package com.ravinada.sps.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ravinada.sps.BuildConfig
import com.ravinada.sps.domain.NoConnectivityException
import com.ravinada.sps.usecases.GetTrendingTvShowsUseCase
import com.ravinada.sps.usecases.PopularTvShowsResult
import com.ravinada.sps.usecases.SearchTvShowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowsViewModel @Inject constructor(
    private val getTrendingTvShowsUseCase: GetTrendingTvShowsUseCase,
    private val searchTvShowUseCase: SearchTvShowUseCase
) : ViewModel() {

    private val _tvShowsStateResult =
        MutableStateFlow<PopularTvShowsResult>(PopularTvShowsResult.Loading(false))
    val tvShows = _tvShowsStateResult.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000, 1),
        initialValue = PopularTvShowsResult.Loading(false)
    )

    init {
        getTrendingTvShows()
    }

    private fun getTrendingTvShows() = viewModelScope.launch(Dispatchers.IO) {
        getTrendingTvShowsUseCase.invoke(
            api_key = BuildConfig.API_KEY,
            language = "en-US",
        ).onStart {
            _tvShowsStateResult.value = PopularTvShowsResult.Loading(true)
        }.onEach {
            _tvShowsStateResult.value = PopularTvShowsResult.Success(it)
        }.catch {
            when (it) {
                //TODO: Add more cases Internet connection, etc
                is NoConnectivityException -> {
                    _tvShowsStateResult.value = PopularTvShowsResult.InternetError
                }

                else -> {
                    _tvShowsStateResult.value =
                        PopularTvShowsResult.ErrorGeneral(it.message ?: "Error general")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun searchTvShowOrEmpty(query: String) {
        if (query.isEmpty()) {
            getTrendingTvShows()
            return
        }
        // Search tv-show only if query is not empty
        searchTvShow(query)
    }

    private fun searchTvShow(query: String) = viewModelScope.launch(Dispatchers.IO) {
        searchTvShowUseCase.invoke(
            api_key = BuildConfig.API_KEY,
            language = "en-US",
            query = query,
            //page = 1
        ).onStart {
            _tvShowsStateResult.value = PopularTvShowsResult.Loading(true)
        }.onEach {
            if (it.isEmpty()) {
                _tvShowsStateResult.value = PopularTvShowsResult.Empty
                return@onEach
            }
            _tvShowsStateResult.value = PopularTvShowsResult.Success(it)
        }.catch {
            _tvShowsStateResult.value =
                PopularTvShowsResult.ErrorGeneral(it.message ?: "Error general")
        }.launchIn(viewModelScope)
    }
}