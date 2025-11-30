package com.example.personalmanager.data.dao

import androidx.room.*
import com.example.personalmanager.data.model.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM expenses ORDER BY date DESC")
    fun getAllExpenses(): Flow<List<Expense>>
    
    @Query("SELECT * FROM expenses WHERE id = :id")
    suspend fun getExpenseById(id: Int): Expense?
    
    @Query("SELECT SUM(amount) FROM expenses WHERE date >= :startDate AND date <= :endDate")
    fun getTotalExpenseInRange(startDate: Long, endDate: Long): Flow<Double?>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: Expense)
    
    @Update
    suspend fun updateExpense(expense: Expense)
    
    @Delete
    suspend fun deleteExpense(expense: Expense)
}
