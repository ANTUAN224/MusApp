package com.project.musapp.feature.user.register.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
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
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
import com.project.musapp.feature.user.register.presentation.viewmodel.UserRegisterViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Composable
fun UserRegistrationFirstScreen(
    viewModel: UserRegisterViewModel,
    title : String,
    onReturnButtonPress: () -> Unit,
    onFirstRegisterButtonPress: () -> Unit
) {
    Scaffold(topBar = {
        UserRegistrationTopBar(
            onReturnButtonPress = onReturnButtonPress
        )
    }, bottomBar = {}) { innerPadding ->
        UserRegistrationFirstScreenBody(
            viewModel = viewModel,
            title = title,
            onFirstRegisterButtonPress = onFirstRegisterButtonPress
        )
    }
}

@Composable
fun UserRegistrationFirstScreenBody(
    viewModel: UserRegisterViewModel,
    title : String,
    onFirstRegisterButtonPress: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (firstScreenTitleBox, nameBox, surnameBox, birthdateBox, firstScreenButtonBox) = createRefs()

        Box(
            modifier = Modifier.constrainAs(firstScreenTitleBox) {
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
            modifier = Modifier.constrainAs(nameBox) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(firstScreenTitleBox.bottom)
                bottom.linkTo(parent.bottom)
                verticalBias = 0.1f
            }
        ) {
            NameTextField(viewModel = viewModel)
        }


        Box(
            modifier = Modifier.constrainAs(surnameBox) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(nameBox.bottom)
                bottom.linkTo(parent.bottom)
                verticalBias = 0.1f
            }
        ) {
            SurnameTextField(viewModel = viewModel)
        }

        Box(modifier = Modifier.constrainAs(birthdateBox) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(surnameBox.bottom)
            bottom.linkTo(parent.bottom)
            verticalBias = 0.1f
        }) {
            BirthdateTextField(viewModel = viewModel)
        }

        Box(modifier = Modifier.constrainAs(firstScreenButtonBox) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(birthdateBox.bottom)
            bottom.linkTo(parent.bottom)
            verticalBias = 0.2f
        }) {
            UserRegistrationScreenBodyButton(
                content = "Siguiente", viewModel = viewModel
            ) {
                onFirstRegisterButtonPress()
            }
        }
    }
}

@Composable
fun NameTextField(viewModel: UserRegisterViewModel) {
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
fun SurnameTextField(viewModel: UserRegisterViewModel) {
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
fun BirthdateTextField(viewModel: UserRegisterViewModel) {
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
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        viewModel.onDatePickerDialogClosing()
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color.Black
                    )
                ) {
                    Text(text = "Cancelar")
                }
            },
            confirmButton = {
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
            },
            colors = DatePickerDefaults.colors(
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
                }
            )
        }
    }
}