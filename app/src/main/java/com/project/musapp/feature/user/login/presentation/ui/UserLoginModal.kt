package com.project.musapp.feature.user.login.presentation.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import com.project.musapp.feature.user.login.presentation.viewmodel.UserLoginViewModel

@Composable
fun UserLoginModal(viewModel: UserLoginViewModel, onSuccessLogin: () -> Unit) {
    AlertDialog(
        onDismissRequest = { viewModel.onLoginModalClosing() },
        dismissButton = { viewModel.onLoginModalClosing() },
        confirmButton = { viewModel.checkUserLogin() })
}