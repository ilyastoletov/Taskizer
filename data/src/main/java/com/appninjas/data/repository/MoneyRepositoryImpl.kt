package com.appninjas.data.repository

import com.appninjas.data.mapper.MoneyMapper
import com.appninjas.data.network.MoneyClient
import com.appninjas.domain.model.Money
import com.appninjas.domain.repository.MoneyRepository

class MoneyRepositoryImpl(private val moneyApiClient: MoneyClient,
                          private val mapper: MoneyMapper): MoneyRepository {

    override suspend fun getCourse(): Map<String, Money> {
        val apiResponse = moneyApiClient.getMoneyCourse()
        return mapper.mapDtoToCurrencyMap(apiResponse)
    }

}