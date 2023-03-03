package com.appninjas.taskizer.presentation.di

import com.appninjas.domain.usecase.DeleteTaskUseCase
import com.appninjas.domain.usecase.EditTaskUseCase
import com.appninjas.domain.usecase.GetTasksUseCase
import com.appninjas.domain.usecase.SaveTaskUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        SaveTaskUseCase(repository = get())
    }

    factory {
        GetTasksUseCase(repository = get())
    }

    factory {
        EditTaskUseCase(repository = get())
    }

    factory {
        DeleteTaskUseCase(repository = get())
    }

}