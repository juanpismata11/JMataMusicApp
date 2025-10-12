package com.example.musicapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
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
import com.example.musicapp.ui.theme.grayy

@Composable
fun TrackCard(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .height(80.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("*IMAGEN*")

            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
            ) {
                Text(
                    text = "Tales of Ithiria",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Text(
                    text = "Haggard",
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

@Preview
@Composable
fun TrackCardPreview(){
    TrackCard()
}