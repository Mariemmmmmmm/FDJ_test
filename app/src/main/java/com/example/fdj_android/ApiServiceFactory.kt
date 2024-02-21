package com.example.fdj_android

import FootballApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceFactory {
    companion object {
        fun create(): FootballApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com/api/v1/json/50130162/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(FootballApiService::class.java)
        }
    }
}