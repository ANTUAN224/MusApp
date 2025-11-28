package com.project.musapp.feature.auth.presentation.login.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun UserNotFoundModal(onReturnToInitialMenu: () -> Unit) {
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
                text = "Usuario no encontrado",
                color = Color.Black
            )
                },
        text = {
            Text(
                text = "El usuario cuyo correo electrónico y/o contraseña ingresados no se encuentra " +
                        "registrado en nuestro sistema. Vuelve al menú inicial e inténtalo de nuevo.",
                color = Color.Gray
            )
        })
}