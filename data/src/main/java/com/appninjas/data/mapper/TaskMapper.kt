package com.appninjas.data.mapper

import com.appninjas.data.storage.TaskDbModel
import com.appninjas.domain.model.Task

class TaskMapper {

    fun modelToDbModel(taskModel: Task): TaskDbModel = TaskDbModel(
        taskId = taskModel.taskId,
        taskDescription = taskModel.taskDescription,
        taskStatus = taskModel.taskStatus
    )

    fun dbModelToTaskList(dbModel: List<TaskDbModel>): ArrayList<Task> = dbModel.map { dbTask -> Task(
        taskId = dbTask.taskId,
        taskDescription = dbTask.taskDescription,
        taskStatus = dbTask.taskStatus
    ) }.toCollection(ArrayList())

}