package com.project.musapp.feature.user.initialchecking.presentation.ui

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun UserInitialCheckingScreen(
    hasInternetConnection: Boolean?
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            modifier = Modifier.size(60.dp),
            color = Color(color = 0xFF12AA7A),
            trackColor = Color.LightGray,
            strokeWidth = 5.dp
        )
        if (hasInternetConnection == false) ConnectionVerificationModal()
    }
}

@Composable
private fun ConnectionVerificationModal() {
    val activity = LocalActivity.current!!
    AlertDialog(
        onDismissRequest = { activity.finish() },
        confirmButton = { TextButton(onClick = { activity.finish() }) { Text(text = "Cerrar") } },
        title = { Text(text = "Sin conexión a Internet") },
        text = { Text(text = "Actualmente, no tienes acceso a Internet, por lo que no podrás acceder a la app. Verifica tu conexión y vuelva a intentarlo en otra ocasión.") }
    )
}