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
       // MoodEntry("1", R.drawable.awesome_expression, "awesome", Date())
    }

    fun addHardcodedEntry() {
        val selectedImageRes = moodStateHolder.selectedImageRes.value ?: R.drawable.ic_launcher_background
        val text = moodStateHolder.text.value ?: ""
        val hardcodedEntry = MoodEntry(
            id = "1",
            selectedImagesRes = selectedImageRes,
            feelingName = text,
            dateTime = Date(),
            reason = ""
        )
        moodStateHolder.addMoodEntry(hardcodedEntry)
    }

    fun addMoodEntry(){

        val selectedImageRes = moodStateHolder.selectedImageRes.value ?: R.drawable.ic_launcher_background
        val text = moodStateHolder.text.value ?: ""

        val newEntry = MoodEntry(
            id = UUID.randomUUID().toString(),
            selectedImagesRes = selectedImageRes,
            feelingName = text,
            dateTime = Date(),
            reason = ""
        )

        moodStateHolder.addMoodEntry(newEntry)
        // TODO: navigate to currentScreen
    }



}


