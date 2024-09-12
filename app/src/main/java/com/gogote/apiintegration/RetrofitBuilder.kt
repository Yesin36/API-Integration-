package com.gogote.apiintegration

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    val api ="https://quotable.io/"


     fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(api)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}