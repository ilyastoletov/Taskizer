package com.appninjas.domain.model

data class Task(
    val taskId: Int = 0,
    val taskDescription: String,
    var taskStatus: Boolean = false
    )
