package com.ravinada.sps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ravinada.sps.presentation.navigation.RootNavigationGraph
import com.ravinada.sps.ui.theme.TvShowsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TvShowsTheme {
                // A surface container using the 'background' color from the theme
                RootNavigationGraph()
            }
        }
    }
}
