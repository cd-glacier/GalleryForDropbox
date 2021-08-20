package cdglacier.galleryfordropbox.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cdglacier.galleryfordropbox.theme.GalleryTheme

class HomeActivity : AppCompatActivity() {
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
    Column {
        Text(text = "Hello Compose World")
    }
}

@Preview
@Composable
private fun GalleryAppPreview() {
    GalleryTheme {
        GalleryApp()
    }
}