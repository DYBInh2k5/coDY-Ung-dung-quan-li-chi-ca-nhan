package com.example.personalmanager.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.personalmanager.ui.screen.*
import com.example.personalmanager.ui.viewmodel.*

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Tasks : Screen("tasks")
    object Notes : Screen("notes")
    object Expenses : Screen("expenses")
    object Habits : Screen("habits")
    object Reminders : Screen("reminders")
}

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val taskViewModel: TaskViewModel = viewModel()
    val noteViewModel: NoteViewModel = viewModel()
    val expenseViewModel: ExpenseViewModel = viewModel()
    val habitViewModel: HabitViewModel = viewModel()
    val reminderViewModel: ReminderViewModel = viewModel()
    
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToTasks = { navController.navigate(Screen.Tasks.route) },
                onNavigateToNotes = { navController.navigate(Screen.Notes.route) },
                onNavigateToExpenses = { navController.navigate(Screen.Expenses.route) },
                onNavigateToHabits = { navController.navigate(Screen.Habits.route) },
                onNavigateToReminders = { navController.navigate(Screen.Reminders.route) }
            )
        }
        
        composable(Screen.Tasks.route) {
            TaskScreen(
                viewModel = taskViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.Notes.route) {
            NoteScreen(
                viewModel = noteViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.Expenses.route) {
            ExpenseScreen(
                viewModel = expenseViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.Habits.route) {
            HabitScreen(
                viewModel = habitViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.Reminders.route) {
            ReminderScreen(
                viewModel = reminderViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
