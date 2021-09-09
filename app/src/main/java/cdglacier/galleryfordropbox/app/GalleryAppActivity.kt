package cdglacier.galleryfordropbox.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
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
import cdglacier.galleryfordropbox.BuildConfig
import cdglacier.galleryfordropbox.data.medium.MediumRepositoryImpl
import cdglacier.galleryfordropbox.gallery.GalleryScreen
import cdglacier.galleryfordropbox.medium.detail.MediumDetailActivity
import cdglacier.galleryfordropbox.model.Medium
import cdglacier.galleryfordropbox.theme.GalleryTheme
import cdglacier.galleryfordropbox.ui.Footer
import com.dropbox.core.DbxRequestConfig
import com.dropbox.core.android.Auth
import com.dropbox.core.oauth.DbxCredential
import com.dropbox.core.v2.DbxClientV2

class GalleryAppActivity : AppCompatActivity() {
    private val DROPBOX_KEY = BuildConfig.DROPBOX_KEY
    private val clientIdentifier = "GalleryBox/1.0.0"

    private val viewModel: GalleryAppViewModel by lazy {
        val mediumRepository = MediumRepositoryImpl(dropbox = null)
        val factory = GalleryAppViewModel.Factory(application, mediumRepository)
        ViewModelProvider(this, factory)[GalleryAppViewModel::class.java]
    }

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GalleryTheme {
                GalleryApp(
                    viewModel = viewModel,
                    navigateMediumDetail = { navigateMediumDetail(it) },
                    startAuthorization = { startDropboxAuthorization() }
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()

        val localCredential: DbxCredential? = viewModel.getLocalCredential()
        if (localCredential == null) {
            val credential = Auth.getDbxCredential() //fetch the result from the AuthActivity
            credential?.let {
                viewModel.storeCredentialLocally(it)
            }
        }

        val dropbox = createDropboxClient()

        dropbox?.run {
            viewModel.setDropboxClient(this)
            viewModel.initializeMedia()
        }
    }

    private fun createDropboxClient(): DbxClientV2? {
        val requestConfig = DbxRequestConfig(clientIdentifier)
        val credential = viewModel.getLocalCredential()
        return credential?.let {
            DbxClientV2(requestConfig, credential)
        }
    }

    private fun startDropboxAuthorization() {
        val requestConfig = DbxRequestConfig(clientIdentifier)
        val scopes = listOf("account_info.read", "files.content.read", "file_requests.read")
        Auth.startOAuth2PKCE(this, DROPBOX_KEY, requestConfig, scopes)
    }

    private fun navigateMediumDetail(medium: Medium) {
        val intent = Intent(this, MediumDetailActivity::class.java)
        intent.putExtra(MediumDetailActivity.EXTRA_MEDIUM_ID, medium.id)
        startActivity(intent)
    }
}

@ExperimentalFoundationApi
@Composable
private fun GalleryApp(
    viewModel: GalleryAppViewModel,
    startAuthorization: () -> Unit,
    navigateMediumDetail: (Medium) -> Unit
) {
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
                    media = media,
                    onMediumClick = navigateMediumDetail
                )
            }

            composable(GalleryAppScreen.Setting.name) {
                Row {
                    Button(onClick = startAuthorization) {
                        Text(text = "AUTH")
                    }

                    Button(onClick = { viewModel.revokeDropbox() }) {
                        Text(text = "REVOKE")
                    }
                }
            }
        }
    }
}

