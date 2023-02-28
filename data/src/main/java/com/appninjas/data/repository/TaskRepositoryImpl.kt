package com.appninjas.data.repository

import android.content.Context
import com.appninjas.domain.model.Task
import com.appninjas.domain.repository.TaskRepository

class TaskRepositoryImpl(private val context: Context): TaskRepository {
    override suspend fun saveTask(task: Task) {
        TODO("Not yet implemented")
    }
}