package com.project.musapp.core.component.presentation.ui

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CommonCircularProgressIndicator() {
    CircularProgressIndicator(
        modifier = Modifier.size(60.dp),
        color = Color(color = 0xFF12AA7A),
        trackColor = Color.LightGray,
        strokeWidth = 5.dp
    )
}

@Composable
fun CommonNoInternetConnectionModal() {
    val activity = LocalActivity.current!!
    AlertDialog(
        onDismissRequest = { activity.finish() },
        confirmButton = { TextButton(onClick = { activity.finish() }) { Text(text = "Cerrar") } },
        title = { Text(text = "Sin conexión a Internet") },
        text = { Text(text = "Actualmente, no tienes acceso a Internet, por lo que no podrás acceder a la app. Verifica tu conexión y vuelva a intentarlo en otra ocasión.") }
    )
}

@Composable
fun BoldText(text: String) {
    Text(text = text, fontWeight = FontWeight.Bold)
}