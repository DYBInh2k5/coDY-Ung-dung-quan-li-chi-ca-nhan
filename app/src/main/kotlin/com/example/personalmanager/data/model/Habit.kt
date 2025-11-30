package com.example.personalmanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String = "",
    val targetDays: Int = 30,
    val currentStreak: Int = 0,
    val bestStreak: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "habit_logs")
data class HabitLog(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val habitId: Int,
    val date: Long = System.currentTimeMillis(),
    val completed: Boolean = true
)
