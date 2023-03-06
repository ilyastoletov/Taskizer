package com.appninjas.domain.usecase

import com.appninjas.domain.model.Task
import com.appninjas.domain.repository.TaskRepository

class EditTaskUseCase(private val repository: TaskRepository) {
    suspend fun invoke(model: Task) = repository.editTask(model)
}