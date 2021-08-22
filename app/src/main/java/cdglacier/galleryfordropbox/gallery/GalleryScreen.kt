package cdglacier.galleryfordropbox.gallery

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cdglacier.galleryfordropbox.theme.GalleryTheme

@Composable
fun GalleryScreen() {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {

    }
}

@Preview
@Composable
fun GalleryScreenPreview() {
    GalleryTheme {
        GalleryScreen()
    }
}

