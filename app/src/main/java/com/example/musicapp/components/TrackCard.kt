package com.example.musicapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.musicapp.models.Album
import com.example.musicapp.ui.theme.grayy

@Composable
fun TrackCard(
    album: Album?,
    name: String,
    subname: String?,
    image: String?,
    onClick: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .height(80.dp)
            .clickable{
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(true)
                    .build(),
                contentDescription = "Imagen del álbum",
                modifier = Modifier
                    .width(80.dp)
                    .height(90.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Black)
                ,
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
            ) {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Text(
                    text = subname.toString(),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 5.dp),
                    color = grayy
                )
            }
        }

        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = "Reproducir",
            tint = grayy,
            modifier = Modifier.padding(end = 10.dp)
        )


    }
}

//@Preview
//@Composable
//fun TrackCardPreview(){
//    val testProduct = Album(
//        title = "Tales of Ithiria",
//        artist = "Haggard",
//        description = "Camiseta cómoda y de alta calidad.",
//        image = "https://ejemplo.com/camiseta.png",
//        id = "1"
//    )
//
//    MusicAppTheme {
//        TrackCard(
//            album = testProduct
//        )
//    }
//}