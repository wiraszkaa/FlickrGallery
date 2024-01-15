package edu.pwr.jakubwiraszka.flickrgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.pwr.jakubwiraszka.flickrgallery.composables.Photos
import edu.pwr.jakubwiraszka.flickrgallery.ui.theme.FlickrGalleryTheme
import edu.pwr.jakubwiraszka.flickrgallery.viewModel.FlickrViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = FlickrViewModel()

        setContent {
            FlickrGalleryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (viewModel.isLoading.value) {
                        Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                            CircularProgressIndicator()
                        }
                    } else if (viewModel.isError.value) {
                        Text(text = viewModel.errorMessage)
                    } else if (viewModel.flickrData.value != null) {
                        viewModel.flickrData.value!!.items?.let { Photos(photos = it) }
                    }
                }
            }
        }
    }
}