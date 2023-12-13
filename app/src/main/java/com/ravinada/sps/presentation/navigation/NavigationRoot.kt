package com.ravinada.sps.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.ravinada.sps.R
import com.ravinada.sps.presentation.screens.DashboardScreen
import com.ravinada.sps.presentation.screens.DetailsTvShowScreen
import com.ravinada.sps.presentation.screens.FavoritesScreen
import com.ravinada.sps.presentation.screens.TvShowsScreen
import com.ravinada.sps.presentation.viewmodels.DetailsTvShowViewModel
import com.ravinada.sps.presentation.viewmodels.FavoritesViewModel
import com.ravinada.sps.presentation.viewmodels.TvShowsViewModel
import timber.log.Timber

@Composable
fun RootNavigationGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.HOME
    ) {
        composable(route = Graph.HOME) {
            DashboardScreen()
        }
    }
}

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = HomeScreen.TvShowsHomeScreen.route,
    ) {

        composable(HomeScreen.TvShowsHomeScreen.route) {
            val tvShowsViewModel = hiltViewModel<TvShowsViewModel>()
            val state by tvShowsViewModel.tvShows.collectAsStateWithLifecycle()
            TvShowsScreen(
                list = state,
                onClickNavigateToDetails = { tvShowID ->
                    Timber.d("tvShowID saved: $tvShowID")
                    navController.navigate(route = Graph.DETAILS + "/$tvShowID")
                },
                onQueryChange = { query ->
                    tvShowsViewModel.searchTvShowOrEmpty(query)
                }
            )
        }
        composable(HomeScreen.FavoritesHomeScreen.route) {

            val favoritesViewModel = hiltViewModel<FavoritesViewModel>()
            val favoriteTvShows by favoritesViewModel.stateFlow.collectAsStateWithLifecycle()

            FavoritesScreen(
                onClickNavigateToDetails = { tvShowID ->
                    navController.navigate(route = Graph.DETAILS + "/$tvShowID")
                },
                favoriteTvShows = favoriteTvShows
            )
        }
        detailsNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS + "/{tvShowId}",
        startDestination = DetailsScreen.Information.route
    ) {

        composable(DetailsScreen.Information.route) {
            val detailsTvShowViewModel = hiltViewModel<DetailsTvShowViewModel>()
            val stateTvShowDetail by detailsTvShowViewModel.detailsTvShow.collectAsStateWithLifecycle()
            val isFavoriteTvShow by detailsTvShowViewModel.isFavoriteTvShow.collectAsStateWithLifecycle()

            DetailsTvShowScreen(
                navController = navController,
                getDetailsTvShowsResult = stateTvShowDetail,
                onClickFavorite = { tvShowDetail ->
                    detailsTvShowViewModel.saveOrRemoveFavoriteTvShow(tvShowDetail)
                },
                isFavoriteTvShow = isFavoriteTvShow,
            )
        }
    }
}

sealed class DetailsScreen(val route: String) {
    object Information : DetailsScreen("information_screen")
}

sealed class HomeScreen(val route: String, val icon: Int, val title: String) {
    object TvShowsHomeScreen : HomeScreen("tvShows_screen", R.drawable.ic_tv_show, "TvShows")
    object FavoritesHomeScreen : HomeScreen("favorites_screen", R.drawable.ic_love, "Favorites")
}

object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
}
