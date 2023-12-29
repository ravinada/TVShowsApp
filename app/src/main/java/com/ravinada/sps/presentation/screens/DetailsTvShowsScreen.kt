package com.ravinada.sps.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ravinada.sps.domain.TvShowsDetailDomain
import com.ravinada.sps.presentation.composables.CustomErrorScreenSomethingHappens
import com.ravinada.sps.presentation.composables.CustomNoInternetConnectionScreen
import com.ravinada.sps.presentation.composables.LoadingScreen
import com.ravinada.sps.domain.usecases.GetDetailsTvShowsResult

@Composable
fun DetailsTvShowScreen(
    navController: NavController,
    getDetailsTvShowsResult: GetDetailsTvShowsResult,
    onClickFavorite: (TvShowsDetailDomain) -> Unit,
    isFavoriteTvShow: Boolean,
) {
    var isLoading by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }
    var isSuccess by remember { mutableStateOf(false) }
    var isInternetError by remember { mutableStateOf(false) }

    var item by remember { mutableStateOf(TvShowsDetailDomain()) }

    LaunchedEffect(key1 = getDetailsTvShowsResult) {
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
                    title = item.original_name ?: "",
                    description = item.overview ?: "",
                    imageBackdrop = item.backdrop_path ?: "",
                    imagePoster = item.poster_path ?: "",
                    genres = item.genres ?: listOf(),
                    releaseDate = item.first_air_date ?: "",
                    voteAverage = item.vote_average?.toString() ?: "",
                    runtime = item.number_of_seasons ?: "",
                    isFavoriteTvShow = isFavoriteTvShow
                )
            }
        }
    }
}

