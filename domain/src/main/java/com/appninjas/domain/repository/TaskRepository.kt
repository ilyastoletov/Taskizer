package com.appninjas.domain.repository

import com.appninjas.domain.model.Task

interface TaskRepository {
    suspend fun saveTask(task: Task)
    suspend fun getTasks(): List<Task>
}