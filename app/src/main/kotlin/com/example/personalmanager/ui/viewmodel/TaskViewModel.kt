package com.example.personalmanager.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.personalmanager.data.database.AppDatabase
import com.example.personalmanager.data.model.Task
import com.example.personalmanager.data.repository.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TaskRepository
    val allTasks: StateFlow<List<Task>>
    
    init {
        val taskDao = AppDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        allTasks = repository.allTasks.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }
    
    fun addTask(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }
    
    fun updateTask(task: Task) = viewModelScope.launch {
        repository.update(task)
    }
    
    fun deleteTask(task: Task) = viewModelScope.launch {
        repository.delete(task)
    }
    
    fun toggleTaskCompletion(task: Task) = viewModelScope.launch {
        repository.update(task.copy(isCompleted = !task.isCompleted))
    }
}
