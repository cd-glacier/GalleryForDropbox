package cdglacier.galleryfordropbox.gallery

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cdglacier.galleryfordropbox.model.Medium
import cdglacier.galleryfordropbox.model.fakeMedia
import cdglacier.galleryfordropbox.theme.GalleryTheme
import com.google.accompanist.coil.rememberCoilPainter

@ExperimentalFoundationApi
@Composable
fun GalleryScreen(
    media: List<Medium>
) {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        LazyVerticalGrid(cells = GridCells.Adaptive(minSize = 110.dp)) {
            items(media) {
                Image(
                    painter = rememberCoilPainter(request = it.url, fadeIn = true),
                    contentDescription = "medium",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(90.dp)
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun GalleryScreenPreview() {
    GalleryTheme {
        GalleryScreen(
            media = fakeMedia()
        )
    }
}

