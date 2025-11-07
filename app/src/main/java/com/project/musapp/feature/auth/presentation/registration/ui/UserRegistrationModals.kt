package com.project.musapp.feature.auth.presentation.registration.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun EmailAlreadyInUseModal(onReturnToInitialMenu: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onReturnToInitialMenu() },
        confirmButton = { TextButton(onClick = { onReturnToInitialMenu() }) { Text(text = "Volver al menú inicial") } },
        title = { Text(text = "Email introducido ya existente") },
        text = { Text(text = "El email que has ingresado en el registro ya existe. Vuelve al menú inicial e inténtalo de nuevo.") })
}