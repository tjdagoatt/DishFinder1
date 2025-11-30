package com.example.dishfinder.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Response wrapper from TheMealDB: { "meals": [ ... ] }
data class MealResponse(
    val meals: List<Meal>?
)

interface MealApiService {
    // Search meals by name/keyword
    // Example: https://www.themealdb.com/api/json/v1/1/search.php?s=chicken
    @GET("search.php")
    suspend fun searchMeals(@Query("s") query: String): MealResponse
}

object MealApi {
    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: MealApiService = retrofit.create(MealApiService::class.java)
}
