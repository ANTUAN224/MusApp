package com.project.musapp.feature.home.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.project.musapp.feature.home.presentation.viewmodel.HomeViewModel
import com.project.musapp.feature.user.presentation.model.UserProfileUiModel
import com.project.musapp.ui.commoncomponents.BoldText
import com.project.musapp.ui.commoncomponents.CommonVerticalSpacer
import com.project.musapp.ui.commoncomponents.UserProfileImage

@Composable
fun ContactWithSupportModal(homeViewModel: HomeViewModel) {
    AlertDialog(
        onDismissRequest = { homeViewModel.onContactWithSupportModalClosing() },
        confirmButton = {
            TextButton(
                onClick = { homeViewModel.onContactWithSupportModalClosing() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = "Cerrar"
                )
            }
        },
        title = {
            Text(
                text = "Contacto con el soporte",
                color = Color.Black
            )
        },
        text = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Si experimentas cualquier problema en la app, envía un mensaje a la dirección de " +
                        "correo electrónico antuandev@gmail.com.",
                textAlign = TextAlign.Center,
                color = Color.DarkGray
            )
        }
    )
}

@Composable
fun UserProfileModal(userProfile: UserProfileUiModel, homeViewModel: HomeViewModel) {
    AlertDialog(
        onDismissRequest = { homeViewModel.onUserProfileModalClosing() },
        confirmButton = {
            TextButton(
                onClick = { homeViewModel.onUserProfileModalClosing() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                    contentColor = Color.Black
                )
            ) { Text(text = "Cerrar") }
        },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Perfil",
                    color = Color.Black
                )
                Spacer(modifier = Modifier.weight(1f))
                UserProfileImage(userProfileImageUri = userProfile.profileImageUrl, size = 100.dp)
            }
        },
        text = {
            Column {
                Row {
                    BoldText(text = "Nombre")
                    Text(
                        text = ": ${userProfile.name}.",
                        color = Color.DarkGray
                    )
                }
                CommonVerticalSpacer(height = 23.dp)
                Row {
                    BoldText(text = "Apellidos")
                    Text(
                        text = ": ${userProfile.surnames}.",
                        color = Color.DarkGray
                    )
                }
                CommonVerticalSpacer(height = 23.dp)
                Row {
                    BoldText(text = "Fecha de nacimiento")
                    Text(
                        text = ": ${userProfile.birthdateText}.",
                        color = Color.DarkGray
                    )
                }
                CommonVerticalSpacer(height = 23.dp)
                Row {
                    BoldText(text = "Correo")
                    Text(
                        text = ": ${userProfile.email}.",
                        color = Color.DarkGray
                    )
                }
            }
        }
    )
}