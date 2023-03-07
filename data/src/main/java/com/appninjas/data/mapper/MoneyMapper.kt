package com.appninjas.data.mapper

import com.appninjas.data.network.model.MoneyDto
import com.appninjas.domain.model.Money

class MoneyMapper {
    fun mapDtoToCurrencyMap(moneyDto: MoneyDto): Map<String, Money> = mapOf(
        "dollar" to Money(moneyDto.currencyField.dollarCourse.course.toString().dropLast(2).toDouble()),
        "euro" to Money(moneyDto.currencyField.euroCourse.course.toString().dropLast(2).toDouble())
    )
}