package com.appninjas.domain.usecase

import com.appninjas.domain.model.Money
import com.appninjas.domain.repository.MoneyRepository

class GetMoneyCourseUseCase(private val repository: MoneyRepository) {
    suspend fun invoke(): Map<String, Money> = repository.getCourse()
}