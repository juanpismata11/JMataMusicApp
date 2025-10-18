package com.example.musicapp.components

import android.util.Log
import android.view.RoundedCorner
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.musicapp.models.Album
import com.example.musicapp.ui.theme.MusicAppTheme
import com.example.musicapp.ui.theme.purrrple
import com.example.musicapp.ui.theme.surface


//usa como parametros el titulo de la cancion, su artista y la foto
//Si no carga la foto haz otro box, como se hace concha
@Composable
fun AlbumCard(
    album: Album,
    onClick: () -> Unit,
    paddingg: Int
){
    Box(
        modifier = Modifier
            .width(240.dp)
            .height(200.dp)
            .clip(RoundedCornerShape(24.dp))
            .padding(end = paddingg.dp)
            .clickable{
                onClick()
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = "https://static.vecteezy.com/system/resources/thumbnails/004/141/669/small_2x/no-photo-or-blank-image-icon-loading-images-or-missing-image-mark-image-not-available-or-image-coming-soon-sign-simple-nature-silhouette-in-frame-isolated-illustration-vector.jpg",
            contentDescription = "Imagen no disponible",
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(24.dp)),
            contentScale = ContentScale.Crop
        )

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(album.image)
                .crossfade(true)
                .build(),
            contentDescription = "Imagen del álbum",
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(24.dp)),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(20.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(purrrple.copy(alpha = 0.6f))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = album.title,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = album.artist,
                        color = surface,
                        fontSize = 12.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )
                { Icon (
                    modifier = Modifier
                        .size(25.dp)
                        .align(Alignment.Center),
                    imageVector = Icons.Rounded.PlayArrow,
                    contentDescription = "Reproducir"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AlbumCardPreview(){
    val testProduct = Album(
        title = "Tales of Ithiria",
        artist = "Haggard",
        description = "Camiseta cómoda y de alta calidad.",
        image = "https://ejemplo.com/camiseta.png",
        id = "1"
    )

    MusicAppTheme {
        AlbumCard(
            album = testProduct,
            onClick = {},
            paddingg = 24
        )
    }


}