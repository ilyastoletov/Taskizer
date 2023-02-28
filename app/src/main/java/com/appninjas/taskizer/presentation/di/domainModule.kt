package com.appninjas.taskizer.presentation.di

import com.appninjas.domain.usecase.SaveTaskUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        SaveTaskUseCase(repository = get())
    }
}