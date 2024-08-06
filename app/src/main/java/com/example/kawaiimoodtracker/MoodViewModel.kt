package com.example.kawaiimoodtracker

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID

class MoodViewModel : ViewModel(){
    val moodStateHolder = MoodStateHolder()

    init {
        // Add a hardcoded entry on initialization
        addHardcodedEntry()
    }

    private fun addHardcodedEntry() {
        val hardcodedEntry = MoodEntry(
            id = "1",
            selectedImagesRes =  R.drawable.awesome_expression,
            feelingName = "Awesome",
            dateTime = Date()
        )
        moodStateHolder.moodEntires += hardcodedEntry
    }

    fun addMoodEntry(){
        val newEntry = MoodEntry(
            id = UUID.randomUUID().toString(),
            selectedImagesRes = moodStateHolder.selectedImageRes,
            feelingName = moodStateHolder.text,
            dateTime = Date()
        )

        moodStateHolder.moodEntires += newEntry
        // TODO: navigate to currentScreen
    }
}


