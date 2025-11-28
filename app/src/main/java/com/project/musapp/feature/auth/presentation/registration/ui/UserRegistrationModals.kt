package com.project.musapp.feature.auth.presentation.registration.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun EmailAlreadyInUseModal(onReturnToInitialMenu: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onReturnToInitialMenu() },
        confirmButton = {
            TextButton(
                onClick = { onReturnToInitialMenu() },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                )
            ) {
                Text(text = "Volver al menú inicial")
            }
        },
        title = {
            Text(
                text = "Error en el registro de usuario",
                color = Color.Black
            )
        },
        text = {
            Text(
                text =
                    "Se ha producido un error en el registro de usuario. Vuelve al menú inicial e " +
                            "inténtalo de nuevo.",
                color = Color.DarkGray
            )
        })
}