package com.appninjas.data.repository

import com.appninjas.data.mapper.TaskMapper
import com.appninjas.data.storage.TaskDao
import com.appninjas.domain.model.Task
import com.appninjas.domain.repository.TaskRepository

class TaskRepositoryImpl(private val taskDao: TaskDao,
                         private val mapper: TaskMapper): TaskRepository {

    override suspend fun saveTask(task: Task) {
        if (task.taskDescription == "") return
        taskDao.saveTask(mapper.modelToDbModel(task))
    }

    override suspend fun getTasks(): List<Task> {
        val dbTaskList = taskDao.getTasksList()
        return mapper.dbModelToTaskList(dbTaskList)
    }

    override suspend fun updateTask(task: Task) {
        if (task.taskDescription == "") return
        taskDao.updateTask(mapper.modelToDbModel(task))
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(mapper.modelToDbModel(task))
    }
}