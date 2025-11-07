package com.project.musapp.feature.home.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.project.musapp.feature.home.presentation.viewmodel.HomeViewModel
import com.project.musapp.feature.profile.presentation.model.UserProfileUiModel
import com.project.musapp.ui.commoncomponents.BoldText
import com.project.musapp.ui.commoncomponents.CommonSpacer
import com.project.musapp.ui.commoncomponents.UserProfileImage

@Composable
fun ContactWithSupportModal(homeViewModel: HomeViewModel) {
    AlertDialog(
        onDismissRequest = { homeViewModel.onContactWithSupportModalClosing() },
        confirmButton = {
            TextButton(onClick = { homeViewModel.onContactWithSupportModalClosing() }) {
                Text(
                    text = "Cerrar"
                )
            }
        },
        title = { Text(text = "Contacto con el soporte") },
        text = {
            Text(
                text = "Si experimentas cualquier problema en la app, envía un mensaje a la dirección de correo" +
                        " electrónico antuandev@gmail.com.",
                textAlign = TextAlign.Center
            )
        }
    )
}

@Composable
fun UserProfileModal(userProfile: UserProfileUiModel, homeViewModel: HomeViewModel) {
    AlertDialog(
        onDismissRequest = { homeViewModel.onUserProfileModalClosing() },
        confirmButton = {
            TextButton(onClick = { homeViewModel.onUserProfileModalClosing() }) {
                Text(
                    text = "Cerrar"
                )
            }
        },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Perfil")
                Spacer(modifier = Modifier.weight(1f))
                UserProfileImage(userProfileImageUri = userProfile.profileImageUrl, size = 100.dp)
            }
        },
        text = {
            Column {
                Row {
                    BoldText(text = "Nombre")
                    Text(text = ": ${userProfile.name}.")
                }
                CommonSpacer()
                Row {
                    BoldText(text = "Apellidos")
                    Text(text = ": ${userProfile.surnames}.")
                }
                CommonSpacer()
                Row {
                    BoldText(text = "Fecha de nacimiento")
                    Text(text = ": ${userProfile.birthdateText}.")
                }
                CommonSpacer()
                Row {
                    BoldText(text = "Correo")
                    Text(text = ": ${userProfile.email}.")
                }
            }
        }
    )
}