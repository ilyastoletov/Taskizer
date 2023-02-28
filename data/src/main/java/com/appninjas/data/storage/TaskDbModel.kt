package com.appninjas.data.storage

data class TaskDbModel(
    val taskId: Int,
    val taskDescription: String,
    val taskStatus: Boolean
)