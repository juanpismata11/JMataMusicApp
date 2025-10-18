package com.example.musicapp.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.musicapp.components.Reproductor
import com.example.musicapp.components.TrackCard
import com.example.musicapp.models.Album
import com.example.musicapp.services.AlbumServices
import com.example.musicapp.ui.theme.LightPurpleRadiante
import com.example.musicapp.ui.theme.PurpleRadiante
import com.example.musicapp.ui.theme.grayy
import com.example.musicapp.ui.theme.purrrple
import com.example.musicapp.ui.theme.surface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun AlbumScreen(
    id: String,
    navController: NavController
){
    val testProduct = Album(
        title = "Tales of Ithiria",
        artist = "Haggard",
        description = "Camiseta cómoda y de alta calidad.",
        image = "https://ejemplo.com/camiseta.png",
        id = "1"
    )


    var album by remember {
        mutableStateOf<Album?>(null)
    }

    var loading by remember {
        mutableStateOf(true)
    }

    var error by remember {
        mutableStateOf<String?>(null)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = LightPurpleRadiante,
                    start = androidx.compose.ui.geometry.Offset(0f, 0f),
                    end = androidx.compose.ui.geometry.Offset(0f, Float.POSITIVE_INFINITY)
                )
            )
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item{
                LaunchedEffect(id) {
                    loading = true
                    error = null
                    try {
                        val retrofit = Retrofit
                            .Builder()
                            .baseUrl("https://music.juanfrausto.com/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                        val service = retrofit.create(AlbumServices::class.java)
                        val result = service.getAlbumById(id)
                        Log.i("HomeScreen", "Resultado: ${result}")
                        album = result
                    } catch (e: Exception) {
                        error = e.message ?: "Error desconocido"
                        Log.e("HomeScreen", "Error en API: $e")
                    } finally {
                        loading = false
                    }
                }
                if (loading) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else if (error != null) {

                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp)
                            .clip(RoundedCornerShape(16.dp))
                    ){
                        AsyncImage(
                            model = "https://static.vecteezy.com/system/resources/thumbnails/004/141/669/small_2x/no-photo-or-blank-image-icon-loading-images-or-missing-image-mark-image-not-available-or-image-coming-soon-sign-simple-nature-silhouette-in-frame-isolated-illustration-vector.jpg",
                            contentDescription = "Imagen no disponible",
                            modifier = Modifier
                                .matchParentSize(),
                            contentScale = ContentScale.Crop
                        )

                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(album?.image)
                                .crossfade(true)
                                .build(),
                            contentDescription = "Imagen del álbum",
                            modifier = Modifier
                                .matchParentSize(),
                            contentScale = ContentScale.Crop
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(
                                onClick = { navController.popBackStack() },
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(Color.Black)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back",
                                    tint = Color.White
                                )
                            }

                            IconButton(
                                onClick = {},
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(Color.Black)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.FavoriteBorder,
                                    contentDescription = "Back",
                                    tint = Color.White
                                )
                            }
                        }

                        Row(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
                                    .background(
                                        brush = Brush.linearGradient(
                                            colors = PurpleRadiante,
                                            start = androidx.compose.ui.geometry.Offset(0f, 0f),
                                            end = androidx.compose.ui.geometry.Offset(0f, Float.POSITIVE_INFINITY)
                                        )
                                    )
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.PlayArrow,
                                    contentDescription = "Reproducir",
                                    modifier = Modifier
                                        .size(30.dp)
                                        .align(Alignment.Center),
                                    tint = Color.White
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
                                    .background(surface)
                            ){
                                Icon(
                                    modifier = Modifier
                                        .size(30.dp)
                                        .align(Alignment.Center),
                                    imageVector = Icons.Rounded.PlayArrow,
                                    contentDescription = "Reproducir"
                                )
                            }
                        }

                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .shadow(
                                elevation = 6.dp,
                                shape = RoundedCornerShape(16.dp),
                                clip = false
                            )
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text ="About this album",
                                color = purrrple,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )

                            Text(
                                modifier = Modifier.padding(top = 8.dp),
                                text = album?.description ?: "",
                                color = grayy

                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
                            text = "Artist:",
                            color = purrrple,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            modifier = Modifier.padding(end = 12.dp),
                            text = album?.artist ?: "",
                            fontSize = 16.sp,
                            color = grayy
                        )
                    }

                    this@LazyColumn.items(10){ i->
                        TrackCard(
                            name = "${album?.title} . Track ${i+1}",
                            subname = album?.artist,
                            image = album?.image
                        )
                    }
            }

        }




        }


        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 10.dp)
                .padding(bottom = 40.dp)
        ) {
            Reproductor()
        }
    }



}

@Preview
@Composable
fun AlbumScreenPreview(){
    AlbumScreen("1", rememberNavController())
}