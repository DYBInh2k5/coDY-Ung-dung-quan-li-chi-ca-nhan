package com.example.personalmanager.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToTasks: () -> Unit,
    onNavigateToNotes: () -> Unit,
    onNavigateToExpenses: () -> Unit,
    onNavigateToHabits: () -> Unit,
    onNavigateToReminders: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "coDY ✨",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold
                    ) 
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Hôm nay bạn muốn làm gì? 🚀",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            GenZFeatureCard(
                emoji = "✅",
                title = "Tasks",
                description = "Crush your to-do list",
                gradientColors = listOf(Color(0xFF8B5CF6), Color(0xFFA78BFA)),
                onClick = onNavigateToTasks
            )
            
            GenZFeatureCard(
                emoji = "📝",
                title = "Notes",
                description = "Viết lách thoải mái",
                gradientColors = listOf(Color(0xFFEC4899), Color(0xFFF472B6)),
                onClick = onNavigateToNotes
            )
            
            GenZFeatureCard(
                emoji = "💸",
                title = "Money",
                description = "Track chi tiêu thông minh",
                gradientColors = listOf(Color(0xFF10B981), Color(0xFF34D399)),
                onClick = onNavigateToExpenses
            )
            
            GenZFeatureCard(
                emoji = "🔥",
                title = "Habits",
                description = "Build streak & level up",
                gradientColors = listOf(Color(0xFFF97316), Color(0xFFFB923C)),
                onClick = onNavigateToHabits
            )
            
            GenZFeatureCard(
                emoji = "⏰",
                title = "Reminders",
                description = "Never miss a thing",
                gradientColors = listOf(Color(0xFF3B82F6), Color(0xFF60A5FA)),
                onClick = onNavigateToReminders
            )
        }
    }
}

@Composable
fun GenZFeatureCard(
    emoji: String,
    title: String,
    description: String,
    gradientColors: List<Color>,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = androidx.compose.ui.graphics.Brush.horizontalGradient(
                        colors = gradientColors
                    )
                )
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = emoji,
                    style = MaterialTheme.typography.displayLarge.copy(fontSize = 48.sp),
                    modifier = Modifier.padding(end = 16.dp)
                )
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }
        }
    }
}
