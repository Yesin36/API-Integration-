package com.gogote.apiintegration

import retrofit2.http.GET

interface Myinterface {
    @GET("/quotes")
    suspend fun getQuotes(): retrofit2.Response<QuoteList>
}