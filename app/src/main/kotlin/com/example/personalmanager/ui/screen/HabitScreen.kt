package com.example.personalmanager.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.personalmanager.data.model.Habit
import com.example.personalmanager.ui.viewmodel.HabitViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitScreen(
    viewModel: HabitViewModel,
    onNavigateBack: () -> Unit
) {
    val habits by viewModel.allHabits.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🔥 Habits", fontWeight = androidx.compose.ui.text.font.FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, "Quay lại")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = androidx.compose.ui.graphics.Color(0xFFF97316),
                    titleContentColor = androidx.compose.ui.graphics.Color.White,
                    navigationIconContentColor = androidx.compose.ui.graphics.Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, "Thêm thói quen")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(habits) { habit ->
                HabitItem(
                    habit = habit,
                    onCheck = { viewModel.logHabitCompletion(habit.id, habit) },
                    onDelete = { viewModel.deleteHabit(habit) }
                )
            }
        }
    }
    
    if (showDialog) {
        AddHabitDialog(
            onDismiss = { showDialog = false },
            onConfirm = { name, description, targetDays ->
                viewModel.addHabit(
                    Habit(
                        name = name,
                        description = description,
                        targetDays = targetDays
                    )
                )
                showDialog = false
            }
        )
    }
}

@Composable
fun HabitItem(
    habit: Habit,
    onCheck: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = habit.name,
                    style = MaterialTheme.typography.titleMedium
                )
                if (habit.description.isNotEmpty()) {
                    Text(
                        text = habit.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Column {
                        Text(
                            text = "🔥 Streak",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                        Text(
                            text = "${habit.currentStreak} days",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                    }
                    Column {
                        Text(
                            text = "🏆 Best",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                        Text(
                            text = "${habit.bestStreak} days",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                    }
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = onCheck) {
                    Icon(
                        Icons.Default.CheckCircle,
                        "Hoàn thành hôm nay",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, "Xóa")
                }
            }
        }
    }
}

@Composable
fun AddHabitDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String, Int) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var targetDays by remember { mutableStateOf("30") }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Thêm thói quen mới") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Tên thói quen") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Mô tả") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = targetDays,
                    onValueChange = { targetDays = it },
                    label = { Text("Mục tiêu (ngày)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    targetDays.toIntOrNull()?.let { days ->
                        onConfirm(name, description, days)
                    }
                },
                enabled = name.isNotBlank() && targetDays.toIntOrNull() != null
            ) {
                Text("Thêm")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Hủy")
            }
        }
    )
}
