package com.project.musapp.feature.user.register.presentation.ui

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
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
import com.project.musapp.feature.user.register.presentation.viewmodel.UserRegisterViewModel

@Composable
fun LastRegisterScreen(
    viewModel: UserRegisterViewModel,
    context: Context,
    title : String,
    onReturnButtonPress: () -> Unit,
    onLastRegisterButtonPress: () -> Unit
) {
    Scaffold(topBar = {
        UserRegistrationTopBar(
            onReturnButtonPress = onReturnButtonPress
        )
    }, bottomBar = {}) { innerPadding ->
        LastRegisterBody(
            viewModel = viewModel,
            title = title,
            context = context
        ) {
            onLastRegisterButtonPress()
        }
    }
}

@Composable
fun LastRegisterBody(
    viewModel: UserRegisterViewModel,
    title : String,
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
            }
        ) {
            UserRegistrationScreenBodyTitle(title = title)
        }

        Box(
            modifier = Modifier.constrainAs(emailBox) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(lastScreenTitleBox.bottom)
                bottom.linkTo(parent.bottom)
                verticalBias = 0.1f
            }
        ) {
            EmailTextField(viewModel = viewModel)
        }


        Box(
            modifier = Modifier.constrainAs(passwordBox) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(emailBox.bottom)
                bottom.linkTo(parent.bottom)
                verticalBias = 0.1f
            }
        ) {
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
fun EmailTextField(viewModel: UserRegisterViewModel) {
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
fun PasswordTextField(viewModel: UserRegisterViewModel) {
    val password = viewModel.password.observeAsState(initial = "").value
    val passwordError = viewModel.passwordError.observeAsState(initial = "").value
    val showPassword = viewModel.showPassword.observeAsState(initial = false).value

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
                        imageVector = Icons.Filled.VisibilityOff,
                        contentDescription = "Icono para mostrar la contraseña"
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        contentDescription = "Icono para ocultar la contraseña"
                    )
                }
            }
        })
}

@Composable
fun ProfileImage(viewModel: UserRegisterViewModel, context: Context) {
    val profileImagePath =
        viewModel.imagePath.observeAsState(initial = "android.resource://${context.packageName}/${R.drawable.default_image}".toUri()).value

    AsyncImage(
        model = profileImagePath,
        contentDescription = "Imagen de perfil",
        modifier = Modifier
            .clip(shape = CircleShape)
            .size(60.dp)
    )
}

@Composable
fun ImageSelectionButton(viewModel: UserRegisterViewModel, context: Context) {
    val isProfileImageSelected = viewModel.isImageSelected.observeAsState(initial = false).value
    val isImageSelectionButtonPressed =
        viewModel.isImageSelectionButtonPressed.observeAsState(initial = false).value

    val imageSelectionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { imagePath ->
        viewModel.setSelectedImage(imagePath)
    }

    if (!isProfileImageSelected) {
        Button(
            modifier = Modifier.height(70.dp),
            onClick = {
                imageSelectionLauncher.launch(input = "image/*")
                viewModel.onImageSelectionButtonPress()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFC0CB),
                contentColor = Color.DarkGray
            ),
            enabled = !isImageSelectionButtonPressed
        ) {
            Text(text = "Añadir imagen")
        }
    } else {
        Button(
            modifier = Modifier.height(70.dp),
            onClick = { viewModel.onImageDeletion(context = context) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFC0CB),
                contentColor = Color.DarkGray
            )
        ) {
            Text(text = "Eliminar imagen")
        }
    }
}