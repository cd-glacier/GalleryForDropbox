package cdglacier.galleryfordropbox.model

import android.graphics.Bitmap

interface Medium {
    val url: String
    val bitmap: Bitmap?
}

data class Photo(
    override val url: String,
    override val bitmap: Bitmap? = null
) : Medium

fun fakeMedia(): List<Medium> = listOf(
    Photo(url = "https://images.unsplash.com/photo-1449452198679-05c7fd30f416?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=3300&q=80"),
    Photo(url = "https://images.unsplash.com/photo-1600091053240-dcd32960b2f5?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=933&q=80"),
    Photo(url = "https://images.unsplash.com/photo-1618343495166-d2fa2cacb9fd?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=934&q=80"),
    Photo(url = "https://images.unsplash.com/photo-1476802320468-7defadce40f7?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80"),
    Photo(url = "https://images.unsplash.com/photo-1609986225129-6e4ce51626db?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1000&q=80"),
)
