package com.appninjas.domain.repository

import com.appninjas.domain.model.Money

interface MoneyRepository {
    suspend fun getCourse(): Map<String, Money>
}