package com.project.musapp.feature.user.registration.presentation.ui

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import com.project.musapp.R
import com.project.musapp.core.feature.navigation.item.presentation.ui.GlobalCircularProgressIndicator
import com.project.musapp.feature.user.registration.presentation.viewmodel.UserRegistrationViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Composable
fun UserRegistrationScreen(
    viewModel: UserRegistrationViewModel,
    topBarTitle: String,
    bodyTitle: String,
    context: Context,
    onReturnButtonPress: () -> Unit,
    isLoading: Boolean,
    navigateToHome: Boolean?,
    onRegisterButtonPress: () -> Unit,
    onReturnToInitialMenu: () -> Unit
) {
    if (!isLoading && navigateToHome == null) {
        Scaffold(topBar = {
            UserRegistrationTopBar(
                title = topBarTitle, onReturnButtonPress = onReturnButtonPress
            )
        }, bottomBar = {}) { innerPadding ->
            UserRegistrationScreenBody(
                viewModel = viewModel, title = bodyTitle, context = context
            ) {
                onRegisterButtonPress()
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            GlobalCircularProgressIndicator()
            if (navigateToHome == false) RegistrationErrorModal { onReturnToInitialMenu() }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserRegistrationTopBar(title: String, onReturnButtonPress: () -> Unit) {
    TopAppBar(
        navigationIcon = {
        IconButton(onClick = { onReturnButtonPress() }) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = "Botón para volver a la pantalla anterior",
                tint = Color.White
            )
        }
    }, title = {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(text = title, color = Color.White)
        }
    }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(color = 0xFF12AA7A))
    )
}

@Composable
fun UserRegistrationScreenBodyTitle(title: String) {
    Text(
        modifier = Modifier.padding(top = 100.dp),
        text = title,
        color = Color.Black,
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun UserRegistrationScreenBody(
    viewModel: UserRegistrationViewModel,
    title: String,
    context: Context,
    onLastRegisterButtonPress: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), contentAlignment = Alignment.Center
        ) {
            UserRegistrationScreenBodyTitle(title = title)
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.5f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val fieldNames = getFieldNames()

            fieldNames.forEach { fieldName ->
                item {
                    FieldView(fieldName = fieldName, viewModel = viewModel)
                    Spacer(modifier = Modifier.height(23.dp))
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ProfileImage(viewModel = viewModel, context = context)
                    ImageSelectionButton(viewModel = viewModel, context = context)
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), contentAlignment = Alignment.Center
        ) {
            UserRegistrationScreenButton(
                content = "Completar el registro", viewModel = viewModel
            ) {
                onLastRegisterButtonPress()
            }
        }
    }
}

fun getFieldNames(): List<String> = listOf(
    "name", "surnames", "birthdate", "email", "password"
)

@Composable
fun FieldView(fieldName: String, viewModel: UserRegistrationViewModel) {
    when (fieldName) {
        "name" -> NameTextField(viewModel = viewModel)
        "surnames" -> SurnameTextField(viewModel = viewModel)
        "birthdate" -> BirthdateTextField(viewModel = viewModel)
        "email" -> EmailTextField(viewModel = viewModel)
        "password" -> PasswordTextField(viewModel = viewModel)
    }
}

@Composable
fun NameTextField(viewModel: UserRegistrationViewModel) {
    val name by viewModel.name.observeAsState(initial = "")
    val nameError by viewModel.nameError.observeAsState(initial = "")

    TextField(
        label = { Text(text = "Nombre*") },
        singleLine = true,
        value = name,
        onValueChange = { viewModel.onNameChange(it) },
        isError = nameError.isNotBlank(),
        colors = TextFieldDefaults.colors(errorSupportingTextColor = Color.Red),
        supportingText = {
            Text(
                text = nameError
            )
        })
}

@Composable
fun SurnameTextField(viewModel: UserRegistrationViewModel) {
    val surnames by viewModel.surnames.observeAsState(initial = "")
    val surnamesError by viewModel.surnamesError.observeAsState(initial = "")

    TextField(
        label = { Text(text = "Apellidos*") },
        singleLine = true,
        value = surnames,
        onValueChange = { viewModel.onSurnamesChange(it) },
        isError = surnamesError.isNotBlank(),
        colors = TextFieldDefaults.colors(errorSupportingTextColor = Color.Red),
        supportingText = {
            Text(
                text = surnamesError
            )
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdateTextField(viewModel: UserRegistrationViewModel) {
    val birthdateText by viewModel.birthdateText.observeAsState(initial = "")
    val birthdateTextError by viewModel.birthdateError.observeAsState(initial = "")
    val showDatePickerDialog by viewModel.showDatePickerDialog.observeAsState(initial = false)

    val datePickerState = rememberDatePickerState()

    TextField(
        placeholder = { Text(text = "Fecha de nacimiento*") },
        value = if (birthdateText.isNotBlank()) LocalDate.parse(birthdateText).format(
            DateTimeFormatter.ofPattern("dd-MM-yyyy")
        ) else "",
        onValueChange = {},
        readOnly = true,
        isError = birthdateTextError.isNotBlank(),
        colors = TextFieldDefaults.colors(errorSupportingTextColor = Color.Red),
        supportingText = {
            Text(
                text = birthdateTextError
            )
        },
        trailingIcon = {
            IconButton(onClick = { viewModel.onDatePickerDialogOpening() }) {
                Icon(
                    imageVector = Icons.Filled.CalendarToday,
                    contentDescription = "Icono para seleccionar la fecha de nacimiento"
                )
            }
        })

    if (showDatePickerDialog) {
        DatePickerDialog(
            onDismissRequest = {
            viewModel.onDatePickerDialogClosing()
        }, dismissButton = {
            TextButton(
                onClick = {
                    viewModel.onDatePickerDialogClosing()
                }, colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White, containerColor = Color.Black
                )
            ) {
                Text(text = "Cancelar")
            }
        }, confirmButton = {
            TextButton(
                onClick = {
                    viewModel.onDatePickerDialogClosing()
                    viewModel.onBirthdateTextChange(
                        birthdateText = Instant.ofEpochMilli(datePickerState.selectedDateMillis!!)
                            .atOffset(
                                ZoneOffset.UTC
                            ).toLocalDate().toString()
                    )
                },
                enabled = datePickerState.selectedDateMillis != null,
                colors = ButtonDefaults.buttonColors(
                    disabledContentColor = Color.Black,
                    disabledContainerColor = Color.LightGray,
                    contentColor = Color.White,
                    containerColor = Color.Black
                )
            ) {
                Text(text = "Aceptar")
            }
        }, colors = DatePickerDefaults.colors(
            selectedDayContentColor = Color.White,
            selectedDayContainerColor = Color.Black,
            selectedYearContentColor = Color.White,
            selectedYearContainerColor = Color.Black
        )
        ) {
            DatePicker(
                state = datePickerState,
                title = { Text(text = "\n  Selecciona tu fecha de nacimiento") },
                dateFormatter = remember {
                    DatePickerDefaults.dateFormatter(
                        selectedDateSkeleton = "dd-MM-yyyy",
                        selectedDateDescriptionSkeleton = "EEEE, dd 'de' MMMM 'de' yyyy"
                    )
                })
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
        visualTransformation = if (showPassword) VisualTransformation.None
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
                containerColor = Color(0xFFFBDEBC), contentColor = Color.DarkGray
            ), enabled = !isImageSelectionButtonPressed
        ) {
            Text(text = "Añadir imagen")
        }
    } else {
        Button(
            modifier = Modifier.height(70.dp),
            onClick = { viewModel.onImageDeletion(context = context) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFBDEBC), contentColor = Color.DarkGray
            )
        ) {
            Text(text = "Eliminar imagen")
        }
    }
}

@Composable
fun UserRegistrationScreenButton(
    content: String, viewModel: UserRegistrationViewModel, onButtonClick: () -> Unit
) {
    val isUserRegistrationScreenButtonEnabled by viewModel.isUserRegistrationScreenButtonEnabled.observeAsState(
        initial = false
    )

    Button(
        modifier = Modifier
            .padding(bottom = 50.dp)
            .size(width = 220.dp, height = 70.dp),
        onClick = { onButtonClick() },
        enabled = isUserRegistrationScreenButtonEnabled,
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

@Composable
fun RegistrationErrorModal(onReturnToInitialMenu: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onReturnToInitialMenu() },
        confirmButton = { TextButton(onClick = { onReturnToInitialMenu() }) { Text(text = "Volver al menú inicial") } },
        title = { Text(text = "Error en el registro") },
        text = { Text(text = "Se ha producido un error durante el registro de usuario. Vuelve al menú inicial e inténtalo de nuevo.") })
}