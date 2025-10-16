package com.project.musapp.feature.user.registration.presentation.ui

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import com.project.musapp.R
import com.project.musapp.core.feature.navigation.item.presentation.ui.GlobalCircularProgressIndicator
import com.project.musapp.feature.user.registration.presentation.viewmodel.UserRegistrationViewModel

@Composable
fun UserRegistrationLastScreen(
    viewModel: UserRegistrationViewModel,
    context: Context,
    title: String,
    isLoading: Boolean,
    navigateToHome: Boolean?,
    onReturnButtonPress: () -> Unit,
    onLastRegisterButtonPress: () -> Unit,
    onReturnToInitialMenu: () -> Unit
) {
    if (!isLoading && navigateToHome == null) {
        Scaffold(topBar = {
            UserRegistrationTopBar(
                onReturnButtonPress = onReturnButtonPress
            )
        }, bottomBar = {}) { innerPadding ->
            UserRegistrationLastScreenBody(
                viewModel = viewModel, title = title, context = context
            ) {
                onLastRegisterButtonPress()
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            GlobalCircularProgressIndicator()
            if (navigateToHome == false) RegistrationErrorModal { onReturnToInitialMenu() }
        }
    }
}

@Composable
fun RegistrationErrorModal(onReturnToInitialMenu: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onReturnToInitialMenu() },
        confirmButton = { TextButton(onClick = { onReturnToInitialMenu() }) { Text(text = "Volver al menú inicial") } },
        title = { Text(text = "Error en el registro") },
        text = { Text(text = "Se ha producido un error durante el registro de usuario. Vuelve al menú inicial e inténtalo de nuevo.") }
    )
}

@Composable
fun UserRegistrationLastScreenBody(
    viewModel: UserRegistrationViewModel,
    title: String,
    context: Context,
    onLastRegisterButtonPress: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (lastScreenTitleBox, emailBox, passwordBox, imageBox, imageSelectionButtonBox, lastScreenButtonBox) = createRefs()

        Box(
            modifier = Modifier.constrainAs(lastScreenTitleBox) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                verticalBias = 0.2f
            }) {
            UserRegistrationScreenBodyTitle(title = title)
        }

        Box(
            modifier = Modifier.constrainAs(emailBox) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(lastScreenTitleBox.bottom)
                bottom.linkTo(parent.bottom)
                verticalBias = 0.1f
            }) {
            EmailTextField(viewModel = viewModel)
        }


        Box(
            modifier = Modifier.constrainAs(passwordBox) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(emailBox.bottom)
                bottom.linkTo(parent.bottom)
                verticalBias = 0.1f
            }) {
            PasswordTextField(viewModel = viewModel)
        }

        Box(modifier = Modifier.constrainAs(imageBox) {
            start.linkTo(parent.start)
            end.linkTo(imageSelectionButtonBox.end)
            top.linkTo(passwordBox.bottom)
            bottom.linkTo(parent.bottom)
            horizontalBias = 0.3f
            verticalBias = 0.1f
        }) {
            ProfileImage(viewModel = viewModel, context = context)
        }

        Box(modifier = Modifier.constrainAs(imageSelectionButtonBox) {
            start.linkTo(imageBox.start)
            end.linkTo(parent.end)
            top.linkTo(passwordBox.bottom)
            bottom.linkTo(parent.bottom)
            horizontalBias = 0.6f
            verticalBias = 0.1f
        }) {
            ImageSelectionButton(viewModel = viewModel, context = context)
        }

        Box(modifier = Modifier.constrainAs(lastScreenButtonBox) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(imageBox.bottom)
            bottom.linkTo(parent.bottom)
            verticalBias = 0.3f
        }) {
            UserRegistrationScreenBodyButton(
                content = "Registrarme", viewModel = viewModel
            ) {
                onLastRegisterButtonPress()
            }
        }
    }
}

@Composable
fun EmailTextField(viewModel: UserRegistrationViewModel) {
    val email by viewModel.email.observeAsState(initial = "")
    val emailError by viewModel.emailError.observeAsState(initial = "")

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
fun PasswordTextField(viewModel: UserRegistrationViewModel) {
    val password by viewModel.password.observeAsState(initial = "")
    val passwordError by viewModel.passwordError.observeAsState(initial = "")
    val showPassword by viewModel.showPassword.observeAsState(initial = false)

    TextField(
        label = { Text(text = "Contraseña*") },
        singleLine = true,
        value = password,
        onValueChange = { viewModel.onPasswordChange(it) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation =
            if (showPassword) VisualTransformation.None
            else PasswordVisualTransformation(),
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
                        imageVector = Icons.Filled.VisibilityOff,
                        contentDescription = "Icono para ocultar la contraseña"
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        contentDescription = "Icono para mostrar la contraseña"
                    )
                }
            }
        })
}

@Composable
fun ProfileImage(viewModel: UserRegistrationViewModel, context: Context) {
    val profileImagePath by viewModel.imagePath.observeAsState(
        initial = ("android.resource://${context.packageName}/" + "${R.drawable.default_image}").toUri()
    )

    AsyncImage(
        model = profileImagePath,
        contentDescription = "Imagen de perfil",
        modifier = Modifier
            .clip(shape = CircleShape)
            .size(60.dp)
    )
}

@Composable
fun ImageSelectionButton(viewModel: UserRegistrationViewModel, context: Context) {
    val isProfileImageSelected by viewModel.isImageSelected.observeAsState(initial = false)
    val isImageSelectionButtonPressed by viewModel.isImageSelectionButtonPressed.observeAsState(
        initial = false
    )

    val imageSelectionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { imagePath ->
        viewModel.setSelectedImage(imagePath)
    }

    if (!isProfileImageSelected) {
        Button(
            modifier = Modifier.height(70.dp), onClick = {
                imageSelectionLauncher.launch(input = "image/*")
                viewModel.onImageSelectionButtonPress()
            }, colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFC0CB), contentColor = Color.DarkGray
            ), enabled = !isImageSelectionButtonPressed
        ) {
            Text(text = "Añadir imagen")
        }
    } else {
        Button(
            modifier = Modifier.height(70.dp),
            onClick = { viewModel.onImageDeletion(context = context) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFC0CB), contentColor = Color.DarkGray
            )
        ) {
            Text(text = "Eliminar imagen")
        }
    }
}