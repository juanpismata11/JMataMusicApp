package com.example.musicapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicapp.components.AlbumCard
import com.example.musicapp.components.TrackCard
import com.example.musicapp.ui.theme.dark
import com.example.musicapp.ui.theme.grayy
import com.example.musicapp.ui.theme.purrrple
import com.example.musicapp.ui.theme.surface

@Composable
fun AlbumScreen(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Black)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text ="About this album",
                        color = purrrple,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text ="Un album clasico que mezcla elementos de musica clasica con death metal melodico",
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
                    modifier = Modifier.padding(8.dp),
                    text = "Artist:",
                    color = purrrple,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier.padding(end = 12.dp),
                    text = "Haggard",
                    fontSize = 16.sp,
                    color = grayy
                )
            }

            TrackCard()
            TrackCard()

        }
    }

}

@Preview
@Composable
fun AlbumScreenPreview(){
    AlbumScreen()
}