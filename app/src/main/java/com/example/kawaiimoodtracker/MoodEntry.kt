package com.example.kawaiimoodtracker

import java.util.Date

data class MoodEntry(
    val id: String,
    val selectedImagesRes: Int,
    val feelingName: String,
    val dateTime: Date,
    var reason: String,
    var quote: String,
    var author: String
)


