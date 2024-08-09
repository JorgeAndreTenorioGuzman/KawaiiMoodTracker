package com.example.kawaiimoodtracker

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.Calendar
import java.util.Date

class MoodStateHolder{
    var showImageSelector by mutableStateOf(false)
    /*var selectedImageRes by mutableStateOf(R.drawable.ic_launcher_background)
    var text by mutableStateOf("")*/

    private val _selectedImageRes = MutableLiveData(R.drawable.ic_launcher_background)
    val selectedImageRes: LiveData<Int> get() = _selectedImageRes

    private val _text = MutableLiveData("")
    val text: LiveData<String> get() = _text


    // Reason Text states
    private val _reasonText = MutableLiveData("")
    val reasonText: LiveData<String> get() = _reasonText

    private val _reasonSubmittedText = MutableLiveData("")
    val reasonSubmittedText: LiveData<String> get() = _reasonSubmittedText

    var reasonSubmitted by mutableStateOf(false)




    private val _moodEntries = MutableLiveData<List<MoodEntry>>(emptyList())
    val moodEntries: LiveData<List<MoodEntry>> get() = _moodEntries

    private val _selectedMoodIndex = MutableLiveData<MoodEntry>()
    val selectedMoodIndex: LiveData<MoodEntry> get() = _selectedMoodIndex

    fun setSelectedMoodIndex(index: MoodEntry) {
        _selectedMoodIndex.value = index
    }


    fun setSelectedImageRes(imageRes: Int) {
        _selectedImageRes.value = imageRes
    }

    fun setText(newText: String) {
        _text.value = newText
    }

    fun setReasonText(newText: String) {
        _reasonText.value = newText
    }

    fun setReasonSubmittedText(newText: String) {
        _reasonSubmittedText.value = newText
    }


    fun addMoodEntry(moodEntry: MoodEntry){

        Log.d("addMoodEntry", "SelectedImageRes: ${_selectedImageRes.value}")
        Log.d("addMoodEntry", "Text: ${_text.value}")

        val currentList = _moodEntries.value.orEmpty()

        val updatedList = currentList + moodEntry

        _moodEntries.value = updatedList
        Log.d("MoodViewModel", "Added entry: $moodEntry")
    }

    val groupedByMonthYear = moodEntries.value?.groupBy { entry ->
        Pair(entry.dateTime.year, entry.dateTime.month)
    }

    val groupedByMonthYearDay = groupedByMonthYear?.mapValues { entry ->
        entry.value.groupBy { moodEntry ->
            moodEntry.dateTime.day
        }
    }



}


