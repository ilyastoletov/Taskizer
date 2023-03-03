package com.appninjas.taskizer.presentation.di

import com.appninjas.taskizer.presentation.activities.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MainViewModel(
            saveTaskUseCase = get(),
            getTasksUseCase = get(),
            editTaskUseCase = get(),
            deleteTaskUseCase = get()
        )
    }
}