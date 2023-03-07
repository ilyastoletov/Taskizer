package com.appninjas.taskizer.presentation.di

import com.appninjas.data.mapper.MoneyMapper
import com.appninjas.data.network.MoneyClient
import com.appninjas.data.repository.MoneyRepositoryImpl
import com.appninjas.domain.repository.MoneyRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single<MoneyRepository> {
        MoneyRepositoryImpl(
            moneyApiClient = get(),
            mapper = MoneyMapper()
        )
    }

    single<MoneyClient> {
        provideMoneyService(retrofit = get())
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://www.cbr-xml-daily.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}

fun provideMoneyService(retrofit: Retrofit): MoneyClient = retrofit.create(MoneyClient::class.java)