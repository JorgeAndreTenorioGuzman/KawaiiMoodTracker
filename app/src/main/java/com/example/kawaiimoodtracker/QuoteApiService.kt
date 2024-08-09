package com.example.kawaiimoodtracker

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface QuoteApiService {

    @Headers("X-Api-key: yF0fIyDR5eR1AFuVd1Qq0g==38feoT5vNZfdCxPp")
    @GET("v1/quotes")
    fun getQuote(
      @Query("category") category: String? = null
    ): Call<List<MoodEntry>>
}

