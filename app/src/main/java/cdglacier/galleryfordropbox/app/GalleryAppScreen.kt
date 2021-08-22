package cdglacier.galleryfordropbox.app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class GalleryAppScreen(
    val icon: ImageVector
) {
    Gallery(
        icon = Icons.Filled.PhotoLibrary
    ),
    Setting(
        icon = Icons.Filled.Settings
    );

    companion object {
        fun fromRoute(route: String?): GalleryAppScreen =
            when (route?.substringBefore("/")) {
                Gallery.name -> Gallery
                Setting.name -> Setting
                null -> Gallery
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}