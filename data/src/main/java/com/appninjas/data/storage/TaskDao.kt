package com.appninjas.data.storage

import com.appninjas.domain.model.Task

interface TaskDao {

    suspend fun saveTask(task: Task)

    suspend fun getTasksList(): List<TaskDbModel>

}