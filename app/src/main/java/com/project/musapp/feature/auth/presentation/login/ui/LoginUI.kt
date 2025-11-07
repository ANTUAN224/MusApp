package com.project.musapp.feature.auth.presentation.login.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.project.musapp.feature.auth.presentation.login.viewmodel.UserLoginViewModel

@Composable
fun LoginModal(
    userLoginViewModel: UserLoginViewModel
) {
    val isLoginAcceptButtonEnabled by userLoginViewModel.isLoginAcceptButtonEnabled.observeAsState(
        initial = false
    )

    AlertDialog(onDismissRequest = { userLoginViewModel.onLoginModalClosing() }, dismissButton = {
        TextButton(
            onClick = { userLoginViewModel.onLoginModalClosing() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black, contentColor = Color.White
            )
        ) {
            Text(text = "Cancelar")
        }
    }, confirmButton = {
        TextButton(
            onClick = { userLoginViewModel.onLoginAcceptButtonClick() },
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = Color.LightGray,
                disabledContentColor = Color.Black,
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            enabled = isLoginAcceptButtonEnabled
        ) {
            Text(text = "Aceptar")
        }
    }, title = { Text(text = "Inicio de sesión") }, text = {
        Column {
            LoginEmailTextField(viewModel = userLoginViewModel)
            LoginPasswordTextField(viewModel = userLoginViewModel)
        }
    })
}

@Composable
fun LoginEmailTextField(viewModel: UserLoginViewModel) {
    val email = viewModel.email.observeAsState(initial = "").value
    val emailError = viewModel.emailError.observeAsState(initial = "").value

    TextField(
        label = { Text(text = "Correo electrónico*") },
        singleLine = true,
        value = email,
        onValueChange = { viewModel.onEmailChange(it) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        isError = emailError.isNotBlank(),
        colors = TextFieldDefaults.colors(errorSupportingTextColor = Color.Red),
        supportingText = {
            Text(
                text = emailError
            )
        })
}

@Composable
fun LoginPasswordTextField(viewModel: UserLoginViewModel) {
    val password by viewModel.password.observeAsState(initial = "")
    val passwordError by viewModel.passwordError.observeAsState(initial = "")
    val showPassword by viewModel.showPassword.observeAsState(initial = false)

    TextField(
        label = { Text(text = "Contraseña*") },
        singleLine = true,
        value = password,
        onValueChange = { viewModel.onPasswordChange(it) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
        isError = passwordError.isNotBlank(),
        colors = TextFieldDefaults.colors(errorSupportingTextColor = Color.Red),
        supportingText = {
            Text(
                text = passwordError
            )
        },
        trailingIcon = {
            IconButton(onClick = { viewModel.onPasswordShowingStateChange(showPassword) }) {
                if (showPassword) {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        contentDescription = "Icono para mostrar la contraseña"
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.VisibilityOff,
                        contentDescription = "Icono para ocultar la contraseña"
                    )
                }
            }
        }
    )
}