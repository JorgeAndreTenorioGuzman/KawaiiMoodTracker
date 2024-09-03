package com.example.kawaiimoodtracker

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class MoodEntry(
    @PrimaryKey
    val id: String,
    val selectedImagesRes: Int,
    val feelingName: String,
    val dateTime: Date = Date(),
    var reason: String,
    var quote: String,
    var author: String
)


