package com.jslps.empvisist.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter

@Composable
fun AppImage(
    modifier: Modifier = Modifier,
    url: String? = null,
    drawable: Painter? = null,
    height: Dp = 120.dp,
    width: Dp = 120.dp,
    cornerRadius: Dp = 10.dp,
    background: Color = Color.LightGray,
    contentScale: ContentScale = ContentScale.Crop
) {
    val shape = RoundedCornerShape(cornerRadius)

    when {
        url != null -> {
            AsyncImage(
                model = url,
                contentDescription = "Image",
                modifier = modifier
                    .size(width, height)
                    .clip(shape)
                    .background(background),
                contentScale = contentScale
            )
        }

        drawable != null -> {
            Image(
                painter = drawable,
                contentDescription = "Image",
                modifier = modifier
                    .size(width, height)
                    .clip(shape)
                    .background(background),
                contentScale = contentScale
            )
        }
    }
}
