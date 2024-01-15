package edu.pwr.jakubwiraszka.flickrgallery.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import edu.pwr.jakubwiraszka.flickrgallery.model.Photo

@Composable
fun Photos(photos: List<Photo>) {
    var selectedIndex by remember { mutableStateOf(-1) }

    fun selectPhoto(index: Int) {
        selectedIndex = index
    }

    fun deselectPhoto() {
        selectedIndex = -1
    }

    if (selectedIndex == -1)
    LazyColumn {
        items(photos.size) {
            PhotoCard(photo = photos[it], onClick = { selectPhoto(it) })
        }
    }
    else {
        PhotoCard(photo = photos[selectedIndex], onClick = { deselectPhoto() }, isMax = true)
    }
}
