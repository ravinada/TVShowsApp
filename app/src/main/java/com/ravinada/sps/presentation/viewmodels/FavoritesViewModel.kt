package com.ravinada.sps.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ravinada.sps.domain.local.FavoriteTvShowsEntity
import com.ravinada.sps.usecases.GetFavoriteTvShowsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteTvShowsUseCase: GetFavoriteTvShowsUseCase
) : ViewModel() {

    private val _favoriteMovies = MutableStateFlow<List<FavoriteTvShowsEntity>>(emptyList())
    val favoriteMovies = _favoriteMovies

    init {
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() = viewModelScope.launch(Dispatchers.IO) {
        getFavoriteTvShowsUseCase
            .invoke()
            .onStart {
                //Loading
            }.onEach {
                _favoriteMovies.value = it
            }.catch {
                _favoriteMovies.value = emptyList()
            }.launchIn(viewModelScope)
    }
}