package com.project.musapp.feature.user.register.presentation.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.musapp.feature.user.register.presentation.viewmodel.UserRegisterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterTopBar(onReturnButtonPress: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { onReturnButtonPress() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Botón para volver a la pantalla anterior",
                    tint = Color.White
                )
            }
        },
        title = { Text(text = "            Registro de cuenta", color = Color.White) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(color = 0xFF12AA7A))
    )
}

@Composable
fun RegisterScreenTitle(title: String) {
    Text(text = title, color = Color.Black, fontSize = 40.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun RegisterScreenButton(
    content: String, viewModel: UserRegisterViewModel, onButtonClick: () -> Unit
) {
    val isFirstScreenButtonEnabled =
        viewModel.isFirstRegisterScreenButtonEnabled.observeAsState(initial = false).value

    val isLastScreenButtonEnabled =
        viewModel.isLastRegisterScreenButtonEnabled.observeAsState(initial = false).value

    Button(
        modifier = Modifier
            .width(200.dp)
            .height(70.dp),
        onClick = { onButtonClick() },
        enabled = if (content == "Siguiente") isFirstScreenButtonEnabled else isLastScreenButtonEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.DarkGray
        )
    ) {
        Text(text = content, fontSize = 16.sp)
    }
}