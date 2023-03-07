package com.appninjas.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MoneyDto(
    @SerializedName("Valute")
    @Expose
    val currencyField: Currency
)
