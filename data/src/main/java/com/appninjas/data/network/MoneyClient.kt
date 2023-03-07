package com.appninjas.data.network

import com.appninjas.data.network.model.MoneyDto
import retrofit2.http.GET

interface MoneyClient {

    @GET("https://www.cbr-xml-daily.ru/daily_json.js")
    suspend fun getMoneyCourse(): MoneyDto

}