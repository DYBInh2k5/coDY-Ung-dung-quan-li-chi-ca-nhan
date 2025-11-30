package com.example.personalmanager.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.personalmanager.data.database.AppDatabase
import com.example.personalmanager.data.model.Habit
import com.example.personalmanager.data.repository.HabitRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HabitViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: HabitRepository
    val allHabits: StateFlow<List<Habit>>
    
    init {
        val habitDao = AppDatabase.getDatabase(application).habitDao()
        repository = HabitRepository(habitDao)
        allHabits = repository.allHabits.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }
    
    fun addHabit(habit: Habit) = viewModelScope.launch {
        repository.insert(habit)
    }
    
    fun updateHabit(habit: Habit) = viewModelScope.launch {
        repository.update(habit)
    }
    
    fun deleteHabit(habit: Habit) = viewModelScope.launch {
        repository.delete(habit)
    }
    
    fun logHabitCompletion(habitId: Int, habit: Habit) = viewModelScope.launch {
        repository.logHabit(habitId)
        val newStreak = habit.currentStreak + 1
        repository.update(
            habit.copy(
                currentStreak = newStreak,
                bestStreak = maxOf(newStreak, habit.bestStreak)
            )
        )
    }
}
