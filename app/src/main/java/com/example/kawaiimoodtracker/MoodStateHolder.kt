package com.example.kawaiimoodtracker

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MoodStateHolder{
    var showImageSelector by mutableStateOf(false)
    /*var selectedImageRes by mutableStateOf(R.drawable.ic_launcher_background)
    var text by mutableStateOf("")*/


    private val _selectedImageRes = MutableLiveData(R.drawable.ic_launcher_background)
    val selectedImageRes: LiveData<Int> get() = _selectedImageRes

    private val _text = MutableLiveData("")
    val text: LiveData<String> get() = _text



    private val _moodEntries = MutableLiveData<List<MoodEntry>>(emptyList())
    val moodEntries: LiveData<List<MoodEntry>> get() = _moodEntries

    fun setSelectedImageRes(imageRes: Int) {
        _selectedImageRes.value = imageRes
    }

    fun setText(newText: String) {
        _text.value = newText
    }


    fun addMoodEntry(moodEntry: MoodEntry){

        Log.d("addMoodEntry", "SelectedImageRes: ${_selectedImageRes.value}")
        Log.d("addMoodEntry", "Text: ${_text.value}")

        val currentList = _moodEntries.value.orEmpty()

        val updatedList = currentList + moodEntry

        _moodEntries.value = updatedList
        Log.d("MoodViewModel", "Added entry: $moodEntry")
    }
}

