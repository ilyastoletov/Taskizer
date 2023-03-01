package com.appninjas.domain.usecase

import com.appninjas.domain.model.Task
import com.appninjas.domain.repository.TaskRepository

class GetTasksUseCase(private val repository: TaskRepository) {
    suspend fun invoke(): List<Task> = repository.getTasks()
}