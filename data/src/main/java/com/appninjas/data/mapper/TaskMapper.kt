package com.appninjas.data.mapper

import com.appninjas.data.storage.TaskDbModel
import com.appninjas.domain.model.Task
import kotlin.random.Random

class TaskMapper {

    fun modelToDbModel(taskModel: Task): TaskDbModel = TaskDbModel(
        taskId = Random.nextInt(),
        taskDescription = taskModel.taskDescription,
        taskStatus = false
    )

    fun dbModelToTaskList(dbModel: List<TaskDbModel>): List<Task> = dbModel.map { dbTask -> Task(
        taskDescription = dbTask.taskDescription
    ) }

}