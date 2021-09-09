package cdglacier.galleryfordropbox.medium.detail

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cdglacier.galleryfordropbox.theme.GalleryTheme

class MediumDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GalleryTheme {
                MediumDetail()
            }
        }
    }
}

@Composable
fun MediumDetail() {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Column() {
            Text(
                text = "Media Detail",
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Preview
@Composable
fun MediumDetailPreview() {
    GalleryTheme {
        MediumDetail()
    }
}