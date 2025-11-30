package com.example.personalmanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminders")
data class Reminder(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String = "",
    val dateTime: Long,
    val isCompleted: Boolean = false,
    val repeatType: RepeatType = RepeatType.NONE,
    val createdAt: Long = System.currentTimeMillis()
)

enum class RepeatType(val displayName: String) {
    NONE("Không lặp"),
    DAILY("Hàng ngày"),
    WEEKLY("Hàng tuần"),
    MONTHLY("Hàng tháng")
}
