package cdglacier.galleryfordropbox.gallery

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cdglacier.galleryfordropbox.model.Medium
import cdglacier.galleryfordropbox.model.fakeMedia
import cdglacier.galleryfordropbox.theme.GalleryTheme

@ExperimentalFoundationApi
@Composable
fun GalleryScreen(
    media: List<Medium>?,
    onMediumClick: (Medium) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        media?.let {
            LazyVerticalGrid(cells = GridCells.Adaptive(minSize = 110.dp)) {
                items(it) {
                    Image(
                        it.bitmap.asImageBitmap(),
                        contentDescription = "medium",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(110.dp)
                            .clickable { onMediumClick(it) },
                    )
                }

                item {
                    Spacer(modifier = Modifier.size(300.dp))
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalFoundationApi
@Preview
@Composable
fun GalleryScreenPreview() {
    GalleryTheme {
        GalleryScreen(
            media = fakeMedia(),
            onMediumClick = {}
        )
    }
}

