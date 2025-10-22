package com.project.musapp.feature.initialmenu.presentation.ui

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun InitialMenuScreen(onGoToRegisterButtonPress: () -> Unit, onGoToLoginButtonPress: () -> Unit) {
    val activity = LocalActivity.current!!

    Scaffold(topBar = { InitialMenuTopBar() }) { innerPadding ->
        InitialMenuBody(
            onGoToRegisterButtonPress = onGoToRegisterButtonPress,
            onGoToLoginButtonPress = onGoToLoginButtonPress
        ) {
            activity.finish()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InitialMenuTopBar() {
    TopAppBar(title = {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(
                text = "MusApp",
                color = Color.White
            )
        }
    }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(color = 0xFF12AA7A)))
}

@Composable
fun InitialMenuBody(
    onGoToRegisterButtonPress: () -> Unit,
    onGoToLoginButtonPress: () -> Unit,
    onExitButtonPress: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (initialMenuScreenTitleBox, goToRegisterButtonBox, goToLoginButtonBox, exitButtonBox) = createRefs()

        Box(
            modifier = Modifier.constrainAs(initialMenuScreenTitleBox) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                verticalBias = 0.2f
            }
        ) {
            Text(
                text = "¿Qué quieres hacer?",
                color = Color.Black,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Box(
            modifier = Modifier.constrainAs(goToRegisterButtonBox) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(initialMenuScreenTitleBox.bottom)
                bottom.linkTo(parent.bottom)
                verticalBias = 0.2f
            }
        ) {
            InitialMenuOptionButton(content = "Registrarme") {
                onGoToRegisterButtonPress()
            }
        }


        Box(
            modifier = Modifier.constrainAs(goToLoginButtonBox) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(goToRegisterButtonBox.bottom)
                bottom.linkTo(parent.bottom)
                verticalBias = 0.2f
            }
        ) {
            InitialMenuOptionButton(content = "Iniciar sesión") {
                onGoToLoginButtonPress()
            }
        }

        Box(modifier = Modifier.constrainAs(exitButtonBox) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(goToLoginButtonBox.bottom)
            bottom.linkTo(parent.bottom)
            verticalBias = 0.3f
        }) {
            InitialMenuOptionButton(content = "Salir de la app") {
                onExitButtonPress()
            }
        }
    }
}

@Composable
fun InitialMenuOptionButton(content: String, onButtonPress: () -> Unit) {
    Button(
        modifier = Modifier
            .width(200.dp)
            .height(80.dp),
        onClick = { onButtonPress() }, colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.DarkGray
        )
    ) {
        Text(text = content, fontSize = 16.sp)
    }
}