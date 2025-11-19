package com.project.musapp.feature.collection.presentation.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.project.musapp.feature.collection.presentation.model.CollectionReadingUiModel
import com.project.musapp.feature.collection.presentation.viewmodel.CollectionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionCreationModal(
    collectionViewModel: CollectionViewModel,
    onModalButtonClick: () -> Unit
) {
    val isCollectionCreationModalButtonEnabled by
    collectionViewModel.isCollectionCreationModalButtonEnabled.observeAsState(initial = false)


    AlertDialog(
        onDismissRequest = { collectionViewModel.onCollectionCreationModalClosing() },
        title = { Text(text = "Crear colección") },
        text = {
            CollectionTitleTextField(collectionViewModel)
        },
        confirmButton = {
            TextButton(
                onClick = { onModalButtonClick() },
                enabled = isCollectionCreationModalButtonEnabled,
                colors = ButtonDefaults.buttonColors(
                    disabledContainerColor = Color.LightGray,
                    disabledContentColor = Color.Black,
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Aceptar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = { collectionViewModel.onCollectionCreationModalClosing() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Cancelar")
            }
        }
    )
}

@Composable
fun CollectionTitleTextField(collectionViewModel: CollectionViewModel) {
    val collectionTitle by collectionViewModel.collectionTitle.observeAsState(initial = "")
    val collectionTitleError by collectionViewModel.collectionTitleError.observeAsState(initial = "")

    TextField(
        label = { Text(text = "Título*") },
        singleLine = true,
        value = collectionTitle,
        onValueChange = { collectionViewModel.onCollectionTitleChange(title = it) },
        isError = collectionTitleError.isNotEmpty(),
        colors = TextFieldDefaults.colors(errorSupportingTextColor = Color.Red),
        supportingText = {
            Text(
                text = collectionTitleError
            )
        }
    )
}

@Composable
fun NotAnyCollectionsToRenameModal(collectionViewModel: CollectionViewModel) {
    AlertDialog(
        onDismissRequest = { collectionViewModel.onNotAnyCollectionsToRenameModalClosing() },
        confirmButton = {
            TextButton(onClick = { collectionViewModel.onNotAnyCollectionsToRenameModalClosing() }) {
                Text(text = "Cerrar")
            }
        },
        title = {
            Text(
                text = "Ninguna colección creada",
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = "Asegúrate de que tienes al menos una colección creada antes de querer renombrar una.",
                textAlign = TextAlign.Center
            )
        }
    )
}

@Composable
fun CollectionRenamingOptionModal(
    collectionViewModel: CollectionViewModel,
    userCollections: List<CollectionReadingUiModel>
) {
    val collectionRenamingOptionSelectedIndex by
    collectionViewModel.collectionRenamingOptionSelectedIndex.observeAsState()

    AlertDialog(
        modifier = Modifier.height(height = 400.dp),
        onDismissRequest = { collectionViewModel.onCollectionRenamingOptionModalClosing() },
        title = { Text(text = "Renombrar colección") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(space = 10.dp)) {
                Text(
                    text = "Elige qué colección quieres renombrar:",
                    textAlign = TextAlign.Center
                )

                LazyColumn(modifier = Modifier.weight(weight = 1f)) {
                    userCollections.forEachIndexed { index, collectionDeletionOption ->
                        item {
                            ListItem(
                                modifier = Modifier.clickable {
                                    collectionViewModel
                                        .onCollectionRenamingOptionSelect(
                                            index = index
                                        )
                                },
                                leadingContent = {
                                    Box(
                                        modifier = Modifier
                                            .size(30.dp)
                                            .background(
                                                color = Color.LightGray,
                                                shape = CircleShape
                                            ),
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        Text(text = "${index + 1}")
                                    }
                                },
                                headlineContent = {
                                    Text(text = collectionDeletionOption.title)
                                },

                                trailingContent = {
                                    RadioButton(
                                        selected = index == collectionRenamingOptionSelectedIndex,
                                        onClick = {
                                            if (index != collectionRenamingOptionSelectedIndex) {
                                                collectionViewModel
                                                    .onCollectionRenamingOptionSelect(
                                                        index = index
                                                    )
                                            }
                                        },
                                        colors = RadioButtonDefaults.colors(
                                            selectedColor = Color.Black,
                                            unselectedColor = Color.Black
                                        )
                                    )
                                },
                                colors = ListItemDefaults.colors(
                                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                                )
                            )

                            if (index < userCollections.size - 1) {
                                HorizontalDivider(color = Color.LightGray)
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = { collectionViewModel.onCollectionRenamingModalOpening() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Siguiente")
            }
        },
        dismissButton = {
            TextButton(
                onClick = { collectionViewModel.onCollectionRenamingOptionModalClosing() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Cancelar")
            }
        }
    )
}

@Composable
fun CollectionRenamingModal(
    collectionViewModel: CollectionViewModel,
    onModalButtonClick: () -> Unit
) {
    val isCollectionRenamingModalButtonEnabled by
    collectionViewModel.isCollectionRenamingModalButtonEnabled.observeAsState(initial = false)

    AlertDialog(
        onDismissRequest = { collectionViewModel.onCollectionRenamingModalClosing() },
        title = { Text(text = "Renombrar colección") },
        text = {
            ModifiedCollectionTitleTextField(collectionViewModel)
        },
        confirmButton = {
            TextButton(
                onClick = { onModalButtonClick() },
                enabled = isCollectionRenamingModalButtonEnabled,
                colors = ButtonDefaults.buttonColors(
                    disabledContainerColor = Color.LightGray,
                    disabledContentColor = Color.Black,
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Aceptar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = { collectionViewModel.onCollectionRenamingModalClosing() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Cancelar")
            }
        }
    )
}

@Composable
fun ModifiedCollectionTitleTextField(collectionViewModel: CollectionViewModel) {
    val modifiedCollectionTitle by collectionViewModel.modifiedCollectionTitle.observeAsState()
    val modifiedCollectionTitleError by collectionViewModel.modifiedCollectionTitleError.observeAsState(
        initial = ""
    )

    TextField(
        label = { Text(text = "Título*") },
        singleLine = true,
        value = modifiedCollectionTitle!!,
        onValueChange = { collectionViewModel.onModifiedCollectionTitleChange(title = it) },
        isError = modifiedCollectionTitleError.isNotEmpty(),
        colors = TextFieldDefaults.colors(errorSupportingTextColor = Color.Red),
        supportingText = {
            Text(text = modifiedCollectionTitleError)
        }
    )
}

@Composable
fun NotAnyCollectionsToDeleteModal(collectionViewModel: CollectionViewModel) {
    AlertDialog(
        onDismissRequest = { collectionViewModel.onNotAnyCollectionsToDeleteModalClosing() },
        confirmButton = {
            TextButton(onClick = { collectionViewModel.onNotAnyCollectionsToDeleteModalClosing() }) {
                Text(text = "Cerrar")
            }
        },
        title = {
            Text(
                text = "Ninguna colección creada",
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = "Asegúrate de que tienes al menos una colección creada antes de querer eliminar alguna.",
                textAlign = TextAlign.Center
            )
        }
    )
}

@Composable
fun CollectionBatchDeletionModal(
    collectionViewModel: CollectionViewModel,
    userCollections: List<CollectionReadingUiModel>,
    onModalButtonClick: () -> Unit
) {
    val collectionDeletionOptionCheckedIndexes by
    collectionViewModel.collectionDeletionOptionCheckedIndexes.observeAsState()

    AlertDialog(
        modifier = Modifier.height(height = 400.dp),
        onDismissRequest = { collectionViewModel.onCollectionBatchDeletionModalClosing() },
        title = { Text(text = "Eliminar colecciones") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(space = 10.dp)) {
                Text(
                    text = "Elige qué colección o colecciones quieres eliminar:",
                    textAlign = TextAlign.Center
                )

                LazyColumn(modifier = Modifier.weight(weight = 1f)) {
                    userCollections.forEachIndexed { index, collectionDeletionOption ->
                        item {
                            ListItem(
                                modifier = Modifier.clickable {
                                    collectionViewModel
                                        .onCollectionDeletionOptionCheckedStateChange(
                                            index = index,
                                            hasBeenSelected = !collectionDeletionOptionCheckedIndexes!!.contains(
                                                index
                                            )
                                        )
                                },
                                leadingContent = {
                                    Box(
                                        modifier = Modifier
                                            .size(30.dp)
                                            .background(
                                                color = Color.LightGray,
                                                shape = CircleShape
                                            ),
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        Text(text = "${index + 1}")
                                    }
                                },
                                headlineContent = {
                                    Text(text = collectionDeletionOption.title)
                                },

                                trailingContent = {
                                    Checkbox(
                                        checked = collectionDeletionOptionCheckedIndexes!!.contains(
                                            index
                                        ),
                                        onCheckedChange = {
                                            collectionViewModel
                                                .onCollectionDeletionOptionCheckedStateChange(
                                                    index = index,
                                                    hasBeenSelected = it
                                                )
                                        },
                                        colors = CheckboxDefaults.colors(
                                            checkedColor = Color.Black
                                        )
                                    )
                                },
                                colors = ListItemDefaults.colors(
                                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                                )
                            )

                            if (index < userCollections.size - 1) {
                                HorizontalDivider(color = Color.LightGray)
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onModalButtonClick() },
                enabled = collectionDeletionOptionCheckedIndexes!!.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(
                    disabledContainerColor = Color.LightGray,
                    disabledContentColor = Color.Black,
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Aceptar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = { collectionViewModel.onCollectionBatchDeletionModalClosing() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Cancelar")
            }
        }
    )
}