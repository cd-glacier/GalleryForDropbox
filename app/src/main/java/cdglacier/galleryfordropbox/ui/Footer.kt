package cdglacier.galleryfordropbox.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cdglacier.galleryfordropbox.app.GalleryAppScreen
import cdglacier.galleryfordropbox.theme.GalleryTheme

@Composable
fun Footer(
    allTabs: List<GalleryAppScreen>,
    onTabSelected: (GalleryAppScreen) -> Unit,
    currentScreen: GalleryAppScreen
) {
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Row(
            modifier = Modifier.selectableGroup(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val spaceSize = 90.dp
            Spacer(modifier = Modifier.width(spaceSize))

            allTabs.forEach {
                FooterTab(
                    text = it.name,
                    icon = it.icon,
                    onSelected = { onTabSelected(it) },
                    selected = currentScreen == it
                )

                Spacer(modifier = Modifier.width(spaceSize))
            }
        }
    }
}

@Composable
fun FooterTab(
    text: String,
    icon: ImageVector,
    onSelected: () -> Unit,
    selected: Boolean
) {
    IconButton(
        onClick = onSelected,
        modifier = Modifier
            .fillMaxHeight()
            .width(56.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                modifier = Modifier.size(30.dp)
            )

            Text(
                text = text,
                style = MaterialTheme.typography.caption,
            )
        }
    }
}

@Preview
@Composable
private fun FooterPreview() {
    GalleryTheme {
        Footer(
            allTabs = GalleryAppScreen.values().toList(),
            onTabSelected = {},
            currentScreen = GalleryAppScreen.Gallery
        )
    }
}
