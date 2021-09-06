package cdglacier.galleryfordropbox.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cdglacier.galleryfordropbox.data.medium.FakeMediumRepositoryImpl
import cdglacier.galleryfordropbox.gallery.GalleryScreen
import cdglacier.galleryfordropbox.model.Medium
import cdglacier.galleryfordropbox.theme.GalleryTheme
import cdglacier.galleryfordropbox.ui.Footer

class GalleryAppActivity : AppCompatActivity() {
    private val viewModel: GalleryAppViewModel by lazy {
        val mediumRepository = FakeMediumRepositoryImpl()
       
        val factory = GalleryAppViewModel.Factory(mediumRepository)
        ViewModelProvider(this, factory)[GalleryAppViewModel::class.java]
    }

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.initializeMedia()

        setContent {
            GalleryTheme {
                GalleryApp(viewModel)
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun GalleryApp(viewModel: GalleryAppViewModel) {
    val navController = rememberNavController()
    val backstackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = GalleryAppScreen.fromRoute(
        backstackEntry.value?.destination?.route
    )

    val media: List<Medium>? by viewModel.media.observeAsState(null)

    Scaffold(
        bottomBar = {
            Footer(
                allTabs = GalleryAppScreen.values().toList(),
                onTabSelected = { screen -> navController.navigate(screen.name) },
                currentScreen = currentScreen
            )
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = GalleryAppScreen.Gallery.name
        ) {
            composable(GalleryAppScreen.Gallery.name) {
                GalleryScreen(
                    media = media
                )
            }

            composable(GalleryAppScreen.Setting.name) {
                Text("Setting")
            }
        }
    }
}
