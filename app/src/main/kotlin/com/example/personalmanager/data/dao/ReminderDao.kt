package com.example.personalmanager.data.dao

import androidx.room.*
import com.example.personalmanager.data.model.Reminder
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Query("SELECT * FROM reminders ORDER BY dateTime ASC")
    fun getAllReminders(): Flow<List<Reminder>>
    
    @Query("SELECT * FROM reminders WHERE id = :id")
    suspend fun getReminderById(id: Int): Reminder?
    
    @Query("SELECT * FROM reminders WHERE isCompleted = 0 AND dateTime >= :currentTime ORDER BY dateTime ASC")
    fun getUpcomingReminders(currentTime: Long): Flow<List<Reminder>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: Reminder)
    
    @Update
    suspend fun updateReminder(reminder: Reminder)
    
    @Delete
    suspend fun deleteReminder(reminder: Reminder)
}
