package com.ravinada.sps.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ravinada.sps.R
import com.ravinada.sps.domain.TvShowsDomain
import com.ravinada.sps.presentation.composables.CustomEmptySearchScreen
import com.ravinada.sps.presentation.composables.CustomErrorScreenSomethingHappens
import com.ravinada.sps.presentation.composables.CustomNoInternetConnectionScreen
import com.ravinada.sps.presentation.composables.HorizontalTvShowItem
import com.ravinada.sps.presentation.composables.LoadingScreen
import com.ravinada.sps.usecases.PopularTvShowsResult
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvShowsScreen(
    list: PopularTvShowsResult,
    onClickNavigateToDetails: (Int) -> Unit,
    onQueryChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        var searchQuery by rememberSaveable { mutableStateOf("") }

        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(70.dp)
                .clip(RoundedCornerShape(40.dp)),
            query = searchQuery,
            onQueryChange = { queryChanged ->
                searchQuery = queryChanged // update the query state
                onQueryChange(queryChanged) // call the callback
            },
            onSearch = {
                // Handle search ImeAction.Search here
            },
            active = true,
            onActiveChange = {
            },
            placeholder = { Text("Search for a tv-shows") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        ) {}

        Spacer(modifier = Modifier.height(16.dp))

        HeaderTvShowsScreen(
            searchQuery = searchQuery,
            onClickNavigateToDetails = onClickNavigateToDetails,
            popularTvShowsResult = list
        )
    }
}

@Composable
fun HeaderTvShowsScreen(
    searchQuery: String,
    onClickNavigateToDetails: (Int) -> Unit,
    popularTvShowsResult: PopularTvShowsResult
) {
    var isErrorGeneral by rememberSaveable { mutableStateOf(false) }
    var isSuccess by rememberSaveable { mutableStateOf(false) }
    var isLoading by rememberSaveable { mutableStateOf(false) }
    var isEmpty by rememberSaveable { mutableStateOf(false) }
    var isInternetError by rememberSaveable { mutableStateOf(false) }

    var tvShowsDomainList by rememberSaveable { mutableStateOf(listOf<TvShowsDomain>()) }

    LaunchedEffect(key1 = popularTvShowsResult) {
        when (popularTvShowsResult) {
            PopularTvShowsResult.Empty -> {
                isLoading = false
                isErrorGeneral = false
                isInternetError = false
                isEmpty = true
            }

            is PopularTvShowsResult.ErrorGeneral -> {
                isLoading = false
                isErrorGeneral = true
            }

            PopularTvShowsResult.InternetError -> {
                isErrorGeneral = false
            }

            is PopularTvShowsResult.Loading -> {
                isLoading = popularTvShowsResult.isLoading
                isErrorGeneral = false
            }

            is PopularTvShowsResult.Success -> {
                isLoading = false
                isErrorGeneral = false
                isEmpty = false
                isInternetError = false
                isSuccess = true
                tvShowsDomainList = popularTvShowsResult.list
            }
        }
    }

    when {
        isLoading -> {
            LoadingScreen()
        }

        isErrorGeneral -> {
            CustomErrorScreenSomethingHappens(
                modifier = Modifier.padding(bottom = 180.dp),
            )
        }

        isInternetError -> {
            CustomNoInternetConnectionScreen(
                modifier = Modifier.padding(bottom = 180.dp),
            )
        }

        isEmpty -> {
            CustomEmptySearchScreen(
                Modifier.padding(bottom = 180.dp),
                description = stringResource(
                    id = R.string.empty_screen_description_no_results,
                    searchQuery
                )
            )
        }

        isSuccess -> {
            LazyColumn(
                content = {
                    items(tvShowsDomainList) {

                        HorizontalTvShowItem(
                            title = it.title,
                            description = it.overview,
                            imageUrl = it.poster_path,
                            rating = it.vote_average,
                            releaseDate = it.release_date ?: "",
                            onClick = { onClickNavigateToDetails(it.id) })

                        if (it == tvShowsDomainList.last()) {
                            Spacer(modifier = Modifier.height(80.dp))
                        }
                    }
                })
        }
    }
}

@Preview
@Composable
fun TvShowsScreenPrev() {
    val tests = listOf(
        TvShowsDomain(
            id = 1,
            title = "Doctor Who",
            overview = "The Doctor is a Time Lord: a 900 year old alien with 2 hearts, part of a gifted civilization who mastered time travel. The Doctor saves planets for a living—more of a hobby actually, and the Doctor's very, very good at it.",
            poster_path = "https://image.tmdb.org/t/p/original/4edFyasCrkH4MKs6H4mHqlrxA6b.jpg",
            vote_average = 7.9f,
            release_date = "2022-02-17"
        ),
        TvShowsDomain(
            id = 2,
            title = "Los Farad",
            overview = "The day Oskar, a typical local boy, crosses paths with the mysterious and wealthy Farad family, his life changes forever. Oskar enters a winner-take-all game, the world of international arms trafficking. In Marbella where the Farads live, luxury, adrenaline and intense emotions await him... But also a backside of violence and cynicism that tests his will.",
            poster_path = "https://image.tmdb.org/t/p/original/t2aNPWte1XmVbFL2HMppoQK3PG.jpg",
            vote_average = 6.8f,
            release_date = "2023-12-12"
        ),
    )

    TvShowsScreen(
        list = PopularTvShowsResult.Success(tests),
        onClickNavigateToDetails = {
            Timber.d("onClickNavigateToDetails: $it")
        },
        onQueryChange = {
            Timber.d("onQueryChange: $it")
        }
    )
}