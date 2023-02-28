package com.appninjas.domain.usecase

import com.appninjas.domain.model.Task
import com.appninjas.domain.repository.TaskRepository

class SaveTaskUseCase(private val repository: TaskRepository) {
    suspend fun invoke(task: Task) = repository.saveTask(task)
}