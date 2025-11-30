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
import com.example.personalmanager.data.model.Reminder
import com.example.personalmanager.data.model.RepeatType
import com.example.personalmanager.ui.viewmodel.ReminderViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderScreen(
    viewModel: ReminderViewModel,
    onNavigateBack: () -> Unit
) {
    val reminders by viewModel.allReminders.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("⏰ Reminders", fontWeight = androidx.compose.ui.text.font.FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, "Quay lại")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = androidx.compose.ui.graphics.Color(0xFF3B82F6),
                    titleContentColor = androidx.compose.ui.graphics.Color.White,
                    navigationIconContentColor = androidx.compose.ui.graphics.Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, "Thêm nhắc nhở")
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
            items(reminders) { reminder ->
                ReminderItem(
                    reminder = reminder,
                    onToggle = { viewModel.toggleReminderCompletion(reminder) },
                    onDelete = { viewModel.deleteReminder(reminder) }
                )
            }
        }
    }
    
    if (showDialog) {
        AddReminderDialog(
            onDismiss = { showDialog = false },
            onConfirm = { title, description, dateTime, repeatType ->
                viewModel.addReminder(
                    Reminder(
                        title = title,
                        description = description,
                        dateTime = dateTime,
                        repeatType = repeatType
                    )
                )
                showDialog = false
            }
        )
    }
}

@Composable
fun ReminderItem(
    reminder: Reminder,
    onToggle: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = reminder.isCompleted,
                onCheckedChange = { onToggle() }
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = reminder.title,
                    style = MaterialTheme.typography.titleMedium
                )
                if (reminder.description.isNotEmpty()) {
                    Text(
                        text = reminder.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Icon(
                        Icons.Default.DateRange,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = formatDateTime(reminder.dateTime),
                        style = MaterialTheme.typography.labelSmall
                    )
                    if (reminder.repeatType != RepeatType.NONE) {
                        Text(
                            text = "• ${reminder.repeatType.displayName}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, "Xóa")
            }
        }
    }
}

@Composable
fun AddReminderDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String, Long, RepeatType) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedRepeatType by remember { mutableStateOf(RepeatType.NONE) }
    var expanded by remember { mutableStateOf(false) }
    
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.HOUR, 1)
    val dateTime = calendar.timeInMillis
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Thêm nhắc nhở") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Tiêu đề") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Mô tả") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = selectedRepeatType.displayName,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Lặp lại") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        RepeatType.values().forEach { type ->
                            DropdownMenuItem(
                                text = { Text(type.displayName) },
                                onClick = {
                                    selectedRepeatType = type
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onConfirm(title, description, dateTime, selectedRepeatType) },
                enabled = title.isNotBlank()
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

private fun formatDateTime(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return sdf.format(Date(timestamp))
}
