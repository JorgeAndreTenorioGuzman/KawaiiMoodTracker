package com.example.kawaiimoodtracker

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class MoodStateHolder{
    var showImageSelector by mutableStateOf(false)
    var selectedImageRes by mutableStateOf(R.drawable.ic_launcher_background)
    var text by mutableStateOf("")
    var moodEntires by mutableStateOf<List<MoodEntry>>(emptyList())

}

