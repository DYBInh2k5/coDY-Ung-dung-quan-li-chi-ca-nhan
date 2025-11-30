package com.example.personalmanager.data.repository

import com.example.personalmanager.data.dao.HabitDao
import com.example.personalmanager.data.model.Habit
import com.example.personalmanager.data.model.HabitLog
import kotlinx.coroutines.flow.Flow

class HabitRepository(private val habitDao: HabitDao) {
    val allHabits: Flow<List<Habit>> = habitDao.getAllHabits()
    
    suspend fun insert(habit: Habit) = habitDao.insertHabit(habit)
    
    suspend fun update(habit: Habit) = habitDao.updateHabit(habit)
    
    suspend fun delete(habit: Habit) = habitDao.deleteHabit(habit)
    
    fun getHabitLogs(habitId: Int): Flow<List<HabitLog>> = habitDao.getHabitLogs(habitId)
    
    suspend fun logHabit(habitId: Int) {
        habitDao.insertHabitLog(HabitLog(habitId = habitId))
    }
    
    suspend fun removeHabitLog(habitId: Int, date: Long) {
        habitDao.deleteHabitLogByDate(habitId, date)
    }
}
