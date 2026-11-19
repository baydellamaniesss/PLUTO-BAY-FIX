package com.example.pluto_baydella.data.api

import com.example.pluto_baydella.data.model.CatFactModel
import retrofit2.http.GET

interface CatFactApiService {
    @GET("fact")
    suspend fun getCatFact(): CatFactModel
}