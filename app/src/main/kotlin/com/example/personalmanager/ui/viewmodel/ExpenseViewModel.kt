package com.example.personalmanager.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.personalmanager.data.database.AppDatabase
import com.example.personalmanager.data.model.Expense
import com.example.personalmanager.data.repository.ExpenseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.*

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ExpenseRepository
    val allExpenses: StateFlow<List<Expense>>
    val monthlyTotal: StateFlow<Double?>
    
    init {
        val expenseDao = AppDatabase.getDatabase(application).expenseDao()
        repository = ExpenseRepository(expenseDao)
        allExpenses = repository.allExpenses.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
        
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        val startOfMonth = calendar.timeInMillis
        val endOfMonth = System.currentTimeMillis()
        
        monthlyTotal = repository.getTotalExpenseInRange(startOfMonth, endOfMonth).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0.0
        )
    }
    
    fun addExpense(expense: Expense) = viewModelScope.launch {
        repository.insert(expense)
    }
    
    fun deleteExpense(expense: Expense) = viewModelScope.launch {
        repository.delete(expense)
    }
}
