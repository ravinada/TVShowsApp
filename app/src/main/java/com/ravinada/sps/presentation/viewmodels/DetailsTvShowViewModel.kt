package com.ravinada.sps.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ravinada.sps.domain.TvShowsDetailDomain
import com.ravinada.sps.domain.local.toFavoriteTvShowsEntity
import com.ravinada.sps.domain.usecases.DeleteFavoriteTvShowUseCase
import com.ravinada.sps.domain.usecases.GetDetailsTvShowsResult
import com.ravinada.sps.domain.usecases.GetDetailsTvShowsUseCase
import com.ravinada.sps.domain.usecases.GetFavoriteTvShowByIdUseCase
import com.ravinada.sps.domain.usecases.InsertFavoriteTvShowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class DetailsTvShowViewModel @Inject constructor(
    private val getDetailsTvShowsUseCase: GetDetailsTvShowsUseCase,
    savedStateHandle: SavedStateHandle,
    private val insertFavoriteTvShowUseCase: InsertFavoriteTvShowUseCase,
    private val getFavoriteTvShowUseCase: GetFavoriteTvShowByIdUseCase,
    private val deleteFavoriteTvShowUseCase: DeleteFavoriteTvShowUseCase
) : ViewModel() {

    private val _detailsTvShows =
        MutableStateFlow<GetDetailsTvShowsResult>(GetDetailsTvShowsResult.Loading(false))
    val detailsTvShow = _detailsTvShows.stateIn(
        scope = viewModelScope,
        started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000, 1),
        initialValue = GetDetailsTvShowsResult.Loading(false)
    )

    private val _isFavoriteTvShows = MutableStateFlow(false)
    val isFavoriteTvShow = _isFavoriteTvShows.stateIn(
        scope = viewModelScope,
        started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000, 1),
        initialValue = false
    )

    init {
        val idTvShow = savedStateHandle.get<String>("tvShowId") ?: ""
        start(idTvShow)
    }

    private fun start(idTvShow: String) = viewModelScope.launch {
        getDetailsTvShow(idTvShow).join()
        getFavoriteTvShowById(idTvShow)
    }

    private fun getDetailsTvShow(id: String) = viewModelScope.launch(Dispatchers.IO) {
        getDetailsTvShowsUseCase.invoke(
            id = id
        ).onStart {
            _detailsTvShows.value = GetDetailsTvShowsResult.Loading(true)
            delay(1.seconds) // Just to see the loading screen
        }.onEach {
            _detailsTvShows.value = GetDetailsTvShowsResult.Success(it)
        }.catch {
            _detailsTvShows.value = GetDetailsTvShowsResult.Error("Error, ${it.message}")
        }.launchIn(viewModelScope)
    }

    fun saveOrRemoveFavoriteTvShow(tvShow: TvShowsDetailDomain) = viewModelScope.launch {
        if (isFavoriteTvShow.value) {
            unMarkFavoriteTvShow(tvShow)
            return@launch
        }
        //mark favorite tv-show if is not favorite
        markFavoriteTvShow(tvShow)
    }

    private fun markFavoriteTvShow(tvShowsDetailDomain: TvShowsDetailDomain) =
        viewModelScope.launch(Dispatchers.IO) {
            insertFavoriteTvShowUseCase.invoke(tvShowsDetailDomain.toFavoriteTvShowsEntity())
        }

    private fun unMarkFavoriteTvShow(tvShowsDetailDomain: TvShowsDetailDomain) =
        viewModelScope.launch(Dispatchers.IO) {
            val id = (tvShowsDetailDomain.id ?: 0)
            deleteFavoriteTvShowUseCase.invoke(id)

        }

    private fun getFavoriteTvShowById(id: String) = viewModelScope.launch(Dispatchers.IO) {
        val favoriteTvShow = (id.toIntOrNull() ?: 0)
        getFavoriteTvShowUseCase
            .invoke(favoriteTvShow)
            .onStart {
                _isFavoriteTvShows.value = false
            }.onEach {
                when (it) {
                    null -> _isFavoriteTvShows.value = false
                    else -> _isFavoriteTvShows.value = true
                }
            }.catch {
                _isFavoriteTvShows.value = false
            }.launchIn(viewModelScope)
    }
}