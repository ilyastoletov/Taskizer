package com.appninjas.taskizer.presentation.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appninjas.domain.model.Task
import com.appninjas.domain.usecase.DeleteTaskUseCase
import com.appninjas.domain.usecase.EditTaskUseCase
import com.appninjas.domain.usecase.GetTasksUseCase
import com.appninjas.domain.usecase.SaveTaskUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val saveTaskUseCase: SaveTaskUseCase,
    private val getTasksUseCase: GetTasksUseCase,
    private val editTaskUseCase: EditTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
): ViewModel() {

    private val _taskList: MutableLiveData<ArrayList<Task>> = MutableLiveData()
    val taskList: LiveData<ArrayList<Task>> = _taskList

    fun saveTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            saveTaskUseCase.invoke(task)
        }
    }

    fun getTasksList() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getTasksUseCase.invoke()
            _taskList.postValue(result)
        }
    }

    fun editTask(model: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            editTaskUseCase.invoke(model)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteTaskUseCase.invoke(task)
        }
    }

}