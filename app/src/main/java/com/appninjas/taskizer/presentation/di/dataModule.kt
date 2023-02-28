package com.appninjas.taskizer.presentation.di

import com.appninjas.data.repository.TaskRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single {
        TaskRepositoryImpl(context = get())
    }
}