package com.example.musicapp.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.musicapp.components.AlbumCard
import com.example.musicapp.components.Header
import com.example.musicapp.components.TrackCard
import com.example.musicapp.models.Album
import com.example.musicapp.services.AlbumServices
import com.example.musicapp.ui.theme.dark
import com.example.musicapp.ui.theme.surface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun HomeScreen(
    navController: NavController
){
    val testProduct = Album(
        title = "Tales of Ithiria",
        artist = "Haggard",
        description = "Camiseta cómoda y de alta calidad.",
        image = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEj7ugde1AEMq01vmC8BVEpLdF0Xek6AY9bGlBiOU5KnDJwkIfGTjfPDgNDzOdO_IiigYdzctv_esbkP2J-Q4A7KczJJMotAnqNg7hRCCQYD5Ej8Q4gWq3FidTimmOG79GXLx3jiHwI-hA0/w1200-h630-p-k-no-nu/51TLuWZMYeL._SS500_-tn-600x470-0-FFFFFF.jpg",
        id = "1"
    )

    var albums by remember {
        mutableStateOf(listOf<Album>())
    }

    var loading by remember {
        mutableStateOf(true)
    }

    var error by remember {
        mutableStateOf<String?>(null)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = surface
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .padding(top = 40.dp)
        ) {
            Header()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Albums",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Text(
                    text = "See more",
                    color = dark,
                    fontWeight = FontWeight.Bold
                )
            }

            LaunchedEffect(true) {
                loading = true
                error = null
                try {
                    val retrofit = Retrofit
                        .Builder()
                        .baseUrl("https://music.juanfrausto.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                    val service = retrofit.create(AlbumServices::class.java)
                    val result = async(Dispatchers.IO) {
                        service.getAllAlbums()
                    }
                    Log.i("HomeScreen", "Resultado: ${result.await()}")
                    albums = result.await()
                } catch (e: Exception) {
                    error = e.message ?: "Error desconocido"
                    Log.e("HomeScreen", "Error en API: $e")
                } finally {
                    loading = false
                }
            }

            if (loading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (error != null) {
                AlbumCard(
                    album = testProduct,
                    onClick = {},
                    paddingg = 24
                )
            } else {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 24.dp)
                ) {
                    items(albums) { album ->
                        AlbumCard(
                            album = album,
                            onClick = {
                                navController.navigate(AlbumDetailScreenRoute(album.id))
                            },
                            paddingg = 24
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Recently played",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Text(
                    text = "See more",
                    color = dark,
                    fontWeight = FontWeight.Bold
                )
            }

            TrackCard(testProduct)
        }
    }

}

@Preview
@Composable
fun HeaderPreview(){
    HomeScreen(rememberNavController())
}