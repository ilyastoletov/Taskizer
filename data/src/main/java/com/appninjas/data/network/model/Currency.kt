package com.appninjas.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("USD")
    @Expose
    val dollarCourse: CurrencyOptions,
    @SerializedName("EUR")
    @Expose
    val euroCourse: CurrencyOptions
)
