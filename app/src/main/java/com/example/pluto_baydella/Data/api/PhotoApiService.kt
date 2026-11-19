package com.example.pluto_baydella.data.api

import com.example.pluto_baydella.data.model.PhotoModel
import retrofit2.http.GET

interface PhotoApiService {
    @GET("list")
    suspend fun getPhotos(): List<PhotoModel>
}