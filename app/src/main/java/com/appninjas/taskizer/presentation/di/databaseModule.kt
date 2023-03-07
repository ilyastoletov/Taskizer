package com.appninjas.taskizer.presentation.di

import androidx.room.Room
import com.appninjas.data.mapper.TaskMapper
import com.appninjas.data.repository.TaskRepositoryImpl
import com.appninjas.data.storage.TaskDao
import com.appninjas.data.storage.TaskDatabase
import com.appninjas.domain.repository.TaskRepository
import org.koin.dsl.module

val databaseModule = module {
    single<TaskRepository> {
        TaskRepositoryImpl(
            taskDao = get(),
            mapper = TaskMapper()
        )
    }

    single<TaskDao> {
        val database = get<TaskDatabase>()
        database.getDao()
    }

    single<TaskDatabase> {
        Room.databaseBuilder(context = get(), TaskDatabase::class.java, "products_db").fallbackToDestructiveMigration().build()
    }

}