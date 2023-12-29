package com.ravinada.sps.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ravinada.sps.domain.TvShowsDetailDomain
import com.ravinada.sps.domain.TvShowsDomain
import com.ravinada.sps.domain.usecases.GetDetailsTvShowsResult
import com.ravinada.sps.domain.usecases.SimilarTvShowsResult
import com.ravinada.sps.presentation.composables.CustomErrorScreenSomethingHappens
import com.ravinada.sps.presentation.composables.CustomNoInternetConnectionScreen
import com.ravinada.sps.presentation.composables.LoadingScreen

@Composable
fun DetailsTvShowScreen(
    navController: NavController,
    getDetailsTvShowsResult: GetDetailsTvShowsResult,
    onClickFavorite: (TvShowsDetailDomain) -> Unit,
    isFavoriteTvShow: Boolean,
    similarTvShowsResult: SimilarTvShowsResult
) {
    var isLoading by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }
    var isSuccess by remember { mutableStateOf(false) }
    var isInternetError by remember { mutableStateOf(false) }

    var item by remember { mutableStateOf(TvShowsDetailDomain()) }
    var similarTvShowsList by rememberSaveable { mutableStateOf(listOf<TvShowsDomain>()) }

    LaunchedEffect(key1 = getDetailsTvShowsResult, key2 = similarTvShowsResult) {
        when (getDetailsTvShowsResult) {
            is GetDetailsTvShowsResult.Success -> {
                isLoading = false
                isError = false
                isInternetError = false
                isSuccess = true
                item = getDetailsTvShowsResult.data
            }

            is GetDetailsTvShowsResult.Error -> {
                isLoading = false
                isSuccess = false
                isError = true
                isInternetError = false
            }

            is GetDetailsTvShowsResult.Loading -> {
                isError = false
                isSuccess = false
                isInternetError = true
                isLoading = getDetailsTvShowsResult.isLoading
            }

            is GetDetailsTvShowsResult.InternetError -> {
                isLoading = false
                isError = false
                isInternetError = true
                isSuccess = false
            }
        }
        when (similarTvShowsResult) {
            is SimilarTvShowsResult.Success -> {
                isError = false
                isSuccess = true
                similarTvShowsList = similarTvShowsResult.list
            }

            is SimilarTvShowsResult.Error -> {
                isLoading = false
                isSuccess = false
                isError = true
            }

            else -> {
                // nothing to do
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                LoadingScreen()
            }

            isError -> {
                CustomErrorScreenSomethingHappens()
            }

            isInternetError -> {
                CustomNoInternetConnectionScreen()
            }

            isSuccess -> {
                DetailsTvShowContent(
                    onClickBack = {
                        navController.popBackStack()
                    },
                    onClickFavorite = { onClickFavorite(item) },
                    title = item.originalName ?: "",
                    description = item.overview ?: "",
                    imageBackdrop = item.backdropPath ?: "",
                    imagePoster = item.posterPath ?: "",
                    genres = item.genres ?: listOf(),
                    releaseDate = item.firstAirDate ?: "",
                    voteAverage = item.voteAverage?.toString() ?: "",
                    runtime = item.numberOfSeasons ?: "",
                    isFavoriteTvShow = isFavoriteTvShow,
                    similarTvShowsList = similarTvShowsList
                )
            }
        }
    }
}

