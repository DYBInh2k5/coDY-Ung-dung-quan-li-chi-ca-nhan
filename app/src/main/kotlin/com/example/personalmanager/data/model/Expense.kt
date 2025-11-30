package com.example.personalmanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amount: Double,
    val category: ExpenseCategory,
    val description: String,
    val date: Long = System.currentTimeMillis()
)

enum class ExpenseCategory(val displayName: String) {
    FOOD("Ăn uống"),
    TRANSPORT("Di chuyển"),
    SHOPPING("Mua sắm"),
    ENTERTAINMENT("Giải trí"),
    BILLS("Hóa đơn"),
    HEALTH("Sức khỏe"),
    EDUCATION("Giáo dục"),
    OTHER("Khác")
}
