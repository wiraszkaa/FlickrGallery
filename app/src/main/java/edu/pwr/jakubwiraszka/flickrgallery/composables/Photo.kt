package edu.pwr.jakubwiraszka.flickrgallery.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import edu.pwr.jakubwiraszka.flickrgallery.model.Photo
import edu.pwr.jakubwiraszka.flickrgallery.utils.Utils

@Composable
fun PhotoCard(photo: Photo, onClick: () -> Unit, isMax: Boolean = false) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(photo.media?.m),
            contentDescription = photo.title,
            contentScale = if (isMax) ContentScale.Fit else ContentScale.Crop,
            modifier = buildImageModifier(isMax)
        )

        Text(
            text = photo.title ?: "No title",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "By: ${photo.author}",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Taken: ${Utils.formatDate(photo.dateTaken!!)}",
            style = MaterialTheme.typography.bodyLarge
        )
        Divider()

    }
}

fun buildImageModifier(isMax: Boolean): Modifier {
    var modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp))
    modifier = if (isMax) {
        modifier.fillMaxHeight();
    } else {
        modifier.height(200.dp)
    }

    return modifier
}