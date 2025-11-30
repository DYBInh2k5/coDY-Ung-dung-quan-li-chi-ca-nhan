package com.example.personalmanager.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.personalmanager.data.dao.*
import com.example.personalmanager.data.model.*

@Database(
    entities = [Task::class, Note::class, Expense::class, Habit::class, HabitLog::class, Reminder::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun noteDao(): NoteDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun habitDao(): HabitDao
    abstract fun reminderDao(): ReminderDao
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "personal_manager_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
