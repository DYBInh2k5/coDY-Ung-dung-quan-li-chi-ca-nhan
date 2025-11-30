package com.example.personalmanager.data.repository

import com.example.personalmanager.data.dao.ExpenseDao
import com.example.personalmanager.data.model.Expense
import kotlinx.coroutines.flow.Flow

class ExpenseRepository(private val expenseDao: ExpenseDao) {
    val allExpenses: Flow<List<Expense>> = expenseDao.getAllExpenses()
    
    fun getTotalExpenseInRange(startDate: Long, endDate: Long): Flow<Double?> =
        expenseDao.getTotalExpenseInRange(startDate, endDate)
    
    suspend fun insert(expense: Expense) = expenseDao.insertExpense(expense)
    
    suspend fun update(expense: Expense) = expenseDao.updateExpense(expense)
    
    suspend fun delete(expense: Expense) = expenseDao.deleteExpense(expense)
}
