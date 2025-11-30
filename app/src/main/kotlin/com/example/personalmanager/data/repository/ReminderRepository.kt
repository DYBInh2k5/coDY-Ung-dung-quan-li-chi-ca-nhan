package com.example.personalmanager.data.repository

import com.example.personalmanager.data.dao.ReminderDao
import com.example.personalmanager.data.model.Reminder
import kotlinx.coroutines.flow.Flow

class ReminderRepository(private val reminderDao: ReminderDao) {
    val allReminders: Flow<List<Reminder>> = reminderDao.getAllReminders()
    
    fun getUpcomingReminders(currentTime: Long): Flow<List<Reminder>> =
        reminderDao.getUpcomingReminders(currentTime)
    
    suspend fun insert(reminder: Reminder) = reminderDao.insertReminder(reminder)
    
    suspend fun update(reminder: Reminder) = reminderDao.updateReminder(reminder)
    
    suspend fun delete(reminder: Reminder) = reminderDao.deleteReminder(reminder)
}
