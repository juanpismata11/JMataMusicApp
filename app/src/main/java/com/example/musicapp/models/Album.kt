package com.example.musicapp.models
//https://music.juanfrausto.com/api/albums
//https://music.juanfrausto.com/api/albums/{id}

data class Album(
    val title: String,
    val artist: String,
    val description: String,
    val image: String,
    val id: String
)
