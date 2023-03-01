package com.appninjas.taskizer.presentation.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appninjas.domain.model.Task
import com.appninjas.domain.usecase.GetTasksUseCase
import com.appninjas.domain.usecase.SaveTaskUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val saveTaskUseCase: SaveTaskUseCase,
    private val getTasksUseCase: GetTasksUseCase
): ViewModel() {

    private val _taskList: MutableLiveData<List<Task>> = MutableLiveData()
    val taskList: LiveData<List<Task>> = _taskList

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

}