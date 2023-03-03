package com.appninjas.domain.usecase

import com.appninjas.domain.model.Task
import com.appninjas.domain.repository.TaskRepository

class EditTaskUseCase(private val repository: TaskRepository) {
    suspend fun invoke(task: Task) = repository.updateTask(task)
}