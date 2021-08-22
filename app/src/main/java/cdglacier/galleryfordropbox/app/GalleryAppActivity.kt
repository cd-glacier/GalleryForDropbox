package cdglacier.galleryfordropbox.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cdglacier.galleryfordropbox.theme.GalleryTheme
import cdglacier.galleryfordropbox.ui.Footer

class GalleryAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GalleryTheme {
                GalleryApp()
            }
        }
    }
}

@Composable
private fun GalleryApp() {
    val navController = rememberNavController()
    val backstackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = GalleryAppScreen.fromRoute(
        backstackEntry.value?.destination?.route
    )

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
                Text("Gallery")
            }

            composable(GalleryAppScreen.Setting.name) {
                Text("Setting")
            }
        }
    }
}

@Preview
@Composable
private fun GalleryAppPreview() {
    GalleryTheme {
        GalleryApp()
    }
}