package com.appninjas.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrencyOptions(
    @SerializedName("Value")
    @Expose
    val course: Double
)
