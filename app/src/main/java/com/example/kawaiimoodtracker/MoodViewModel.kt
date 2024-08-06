package com.example.kawaiimoodtracker

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID

class MoodViewModel : ViewModel(){
    val moodStateHolder = MoodStateHolder()

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


