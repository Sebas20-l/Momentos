package com.example.mobilproject1.ui.home.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

fun addEmojiReaction(reactions: Map<String, Int>, emoji: String): Map<String, Int> {
    val updated = reactions.toMutableMap()
    updated[emoji] = (updated[emoji] ?: 0) + 1
    return updated
}

fun getMostPopularReaction(reactions: Map<String, Int>): String? {
    return reactions.maxByOrNull { it.value }?.key
}

fun getTotalReactions(reactions: Map<String, Int>): Int {
    return reactions.values.sum()
}

@Composable
fun HomeView() {
    var reactions by remember { mutableStateOf(mapOf<String, Int>()) }
    val emojis = listOf("😍", "😂", "❤️", "😮", "👏")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Momentos",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Reacciona a esta foto familiar 📸",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "🏖️ Vacaciones 2024", style = MaterialTheme.typography.titleMedium)

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    emojis.forEach { emoji ->
                        Button(onClick = {
                            reactions = addEmojiReaction(reactions, emoji)
                        }) {
                            Text(text = emoji, fontSize = 20.sp)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (reactions.isNotEmpty()) {
                    reactions.forEach { (emoji, count) ->
                        Text(text = "$emoji x$count", style = MaterialTheme.typography.bodyLarge)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "⭐ Más popular: ${getMostPopularReaction(reactions)}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Total: ${getTotalReactions(reactions)} reacciones",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}