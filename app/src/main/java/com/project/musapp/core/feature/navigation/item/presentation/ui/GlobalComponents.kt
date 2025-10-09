package com.project.musapp.core.feature.navigation.item.presentation.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GlobalCircularProgressIndicator() {
    CircularProgressIndicator(
        modifier = Modifier.size(60.dp),
        color = Color(color = 0xFF12AA7A),
        trackColor = Color.LightGray,
        strokeWidth = 5.dp
    )
}