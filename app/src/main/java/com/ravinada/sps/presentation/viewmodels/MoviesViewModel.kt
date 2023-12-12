package com.ravinada.sps.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ravinada.sps.domain.NoConnectivityException
import com.ravinada.sps.usecases.GetTrendingTvShowsUseCase
import com.ravinada.sps.usecases.PopularMoviesResult
import com.ravinada.sps.usecases.SearchTvShowUseCase
import com.ravinada.sps.BuildConfig
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
class MoviesViewModel @Inject constructor(
    private val getTrendingTvShowsUseCase: GetTrendingTvShowsUseCase,
    private val searchTvShowUseCase: SearchTvShowUseCase
) : ViewModel() {

    private val _moviesStateResult =
        MutableStateFlow<PopularMoviesResult>(PopularMoviesResult.Loading(false))
    val movies = _moviesStateResult.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000, 1),
        initialValue = PopularMoviesResult.Loading(false)
    )

    init {
        getTrendingTvShows()
    }

    private fun getTrendingTvShows() = viewModelScope.launch(Dispatchers.IO) {
        getTrendingTvShowsUseCase.invoke(
            api_key = BuildConfig.API_KEY,
            language = "en-US",
        ).onStart {
            _moviesStateResult.value = PopularMoviesResult.Loading(true)
        }.onEach {
            _moviesStateResult.value = PopularMoviesResult.Success(it)
        }.catch {
            when (it) {
                //TODO: Add more cases Internet connection, etc
                is NoConnectivityException -> {
                    _moviesStateResult.value = PopularMoviesResult.InternetError
                }

                else -> {
                    _moviesStateResult.value =
                        PopularMoviesResult.ErrorGeneral(it.message ?: "Error general")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun searchMovieOrEmpty(query: String) {
        if (query.isEmpty()) {
            getTrendingTvShows()
            return
        }
        // Search movie only if query is not empty
        searchMovie(query)
    }

    private fun searchMovie(query: String) = viewModelScope.launch(Dispatchers.IO) {
        searchTvShowUseCase.invoke(
            api_key = BuildConfig.API_KEY,
            language = "en-US",
            query = query,
            //page = 1
        ).onStart {
            _moviesStateResult.value = PopularMoviesResult.Loading(true)
        }.onEach {
            if (it.isEmpty()) {
                _moviesStateResult.value = PopularMoviesResult.Empty
                return@onEach
            }
            _moviesStateResult.value = PopularMoviesResult.Success(it)
        }.catch {
            _moviesStateResult.value =
                PopularMoviesResult.ErrorGeneral(it.message ?: "Error general")
        }.launchIn(viewModelScope)
    }
}