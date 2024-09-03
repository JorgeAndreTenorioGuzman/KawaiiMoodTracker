package com.example.kawaiimoodtracker

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID



class MoodViewModel(application: Application) : AndroidViewModel(application){
    val moodStateHolder = MoodStateHolder(application)

    init {
    viewModelScope.launch {
        moodStateHolder.moodDao.getMoodOrderedByDateTime().observeForever { entries ->
            moodStateHolder.setMoodEntries(moodEntries = entries)

        }
        }
    }


 /*   init {
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
            reason = "",
            quote = "",
            author = "",
        )
        moodStateHolder.addMoodEntry(hardcodedEntry)
    }
*/
    fun addMoodEntry(){

        val selectedImageRes = moodStateHolder.selectedImageRes.value ?: R.drawable.ic_launcher_background
        val text = moodStateHolder.text.value ?: ""

        val newEntry = MoodEntry(
            id = UUID.randomUUID().toString(),
            selectedImagesRes = selectedImageRes,
            feelingName = text,
            dateTime = Date(),
            reason = "",
            quote = "",
            author = ""
        )

        //insert new mood in mood entry list
        moodStateHolder.addMoodEntry(newEntry)
        //insert new mood in Room database
        viewModelScope.launch (Dispatchers.IO){
            moodStateHolder.moodDao.insertMood(newEntry)
        }
    }

    object RetrofitInstance {
        private const val BASE_URL = "https://api.api-ninjas.com/"

        val api: QuoteApiService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(QuoteApiService::class.java)
        }
    }

    fun fetchQuoteAndUpdateMoodEntry(entry: MoodEntry) {
        RetrofitInstance.api.getQuote().enqueue(object : Callback<List<MoodEntry>> {
            override fun onResponse(
                call: Call<List<MoodEntry>>,
                response: Response<List<MoodEntry>>
            ) {
                if (response.isSuccessful) {
                    val fetchedMoodEntry = response.body()?.firstOrNull()
                    fetchedMoodEntry?.let {
                        entry.quote = it.quote
                        entry.author = it.author

                        moodStateHolder.postquote(it.quote)
                        moodStateHolder.postauthor(it.author)
                    }
                }
            }

            override fun onFailure(call: Call<List<MoodEntry>>, t: Throwable) {
                // Handle the error here, maybe set a default message or log the error
                entry.quote = "Failed to fetch quote."
                entry.author = ""
                // You can also notify the UI of the failure here if necessary
            }


        })
    }

}

