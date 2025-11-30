package com.example.personalmanager.data.dao

import androidx.room.*
import com.example.personalmanager.data.model.Habit
import com.example.personalmanager.data.model.HabitLog
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Query("SELECT * FROM habits ORDER BY createdAt DESC")
    fun getAllHabits(): Flow<List<Habit>>
    
    @Query("SELECT * FROM habits WHERE id = :id")
    suspend fun getHabitById(id: Int): Habit?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: Habit)
    
    @Update
    suspend fun updateHabit(habit: Habit)
    
    @Delete
    suspend fun deleteHabit(habit: Habit)
    
    @Query("SELECT * FROM habit_logs WHERE habitId = :habitId ORDER BY date DESC")
    fun getHabitLogs(habitId: Int): Flow<List<HabitLog>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabitLog(log: HabitLog)
    
    @Query("DELETE FROM habit_logs WHERE habitId = :habitId AND date = :date")
    suspend fun deleteHabitLogByDate(habitId: Int, date: Long)
}
