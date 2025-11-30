package com.example.personalmanager.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.personalmanager.data.database.AppDatabase
import com.example.personalmanager.data.model.Reminder
import com.example.personalmanager.data.repository.ReminderRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ReminderViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ReminderRepository
    val allReminders: StateFlow<List<Reminder>>
    val upcomingReminders: StateFlow<List<Reminder>>
    
    init {
        val reminderDao = AppDatabase.getDatabase(application).reminderDao()
        repository = ReminderRepository(reminderDao)
        allReminders = repository.allReminders.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
        upcomingReminders = repository.getUpcomingReminders(System.currentTimeMillis()).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }
    
    fun addReminder(reminder: Reminder) = viewModelScope.launch {
        repository.insert(reminder)
    }
    
    fun updateReminder(reminder: Reminder) = viewModelScope.launch {
        repository.update(reminder)
    }
    
    fun deleteReminder(reminder: Reminder) = viewModelScope.launch {
        repository.delete(reminder)
    }
    
    fun toggleReminderCompletion(reminder: Reminder) = viewModelScope.launch {
        repository.update(reminder.copy(isCompleted = !reminder.isCompleted))
    }
}
