package com.appninjas.taskizer.presentation.di

import com.appninjas.domain.usecase.*
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

    factory {
        GetMoneyCourseUseCase(repository = get())
    }

}