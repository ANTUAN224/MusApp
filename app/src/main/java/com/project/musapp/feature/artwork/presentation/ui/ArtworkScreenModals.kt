package com.project.musapp.feature.artwork.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.project.musapp.feature.artwork.presentation.viewmodel.ArtworkViewModel
import com.project.musapp.feature.collection.presentation.model.CollectionReadingUiModel
import com.project.musapp.ui.commoncomponents.CommonCollectionOptionMultiSelectionModal

@Composable
fun ArtworkDeletionFromCollectionsModal(
    artworkViewModel: ArtworkViewModel,
    collectionsWithThatArtwork: List<CollectionReadingUiModel>,
    onModalAcceptButtonClick: () -> Unit
) {
    val checkedArtworkDeletionCollectionOptionIndexes
            by artworkViewModel.checkedArtworkDeletionCollectionOptionIndexes.observeAsState()

    CommonCollectionOptionMultiSelectionModal(
        title = "Eliminar cuadro",
        text = "Elige en qué colección o colecciones quieres eliminar el cuadro:",
        collectionList = collectionsWithThatArtwork,
        checkedCollectionOptionIndexes = checkedArtworkDeletionCollectionOptionIndexes!!,
        onCollectionOptionCheckedChange = { index, hasBeenSelected ->
            artworkViewModel.onArtworkDeletionCollectionOptionCheckedStateChange(
                index = index,
                hasBeenSelected = hasBeenSelected
            )
        },
        onModalCancelButtonClick = {
            artworkViewModel.onArtworkDeletionFromCollectionsModalClosing()
        },
    ) { onModalAcceptButtonClick() }
}

@Composable
fun ArtworkAdditionToCollectionsModal(
    artworkViewModel: ArtworkViewModel,
    collectionsWithoutThatArtwork: List<CollectionReadingUiModel>,
    onModalAcceptButtonClick: () -> Unit
) {
    val checkedArtworkAdditionCollectionOptionIndexes
            by artworkViewModel.checkedArtworkAdditionCollectionOptionIndexes.observeAsState()

    CommonCollectionOptionMultiSelectionModal(
        title = "Añadir cuadro",
        text = "Elige en qué colección o colecciones quieres añadir el cuadro:",
        collectionList = collectionsWithoutThatArtwork,
        checkedCollectionOptionIndexes = checkedArtworkAdditionCollectionOptionIndexes!!,
        onCollectionOptionCheckedChange = { index, hasBeenSelected ->
            artworkViewModel.onArtworkAdditionCollectionOptionCheckedStateChange(
                index = index,
                hasBeenSelected = hasBeenSelected
            )
        },
        onModalCancelButtonClick = {
            artworkViewModel.onArtworkAdditionToCollectionsModalClosing()
        },
    ) { onModalAcceptButtonClick() }
}

@Composable
fun NotAnyCollectionsCreatedModalInArtworkDeletionOption(artworkViewModel: ArtworkViewModel) {
    AlertDialog(
        onDismissRequest = {
            artworkViewModel.onNotAnyCollectionsCreatedModalInDeletionOptionClosing()
        },
        confirmButton = {
            TextButton(
                onClick = {
                artworkViewModel.onNotAnyCollectionsCreatedModalInDeletionOptionClosing()
            },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                )
            ) {
                Text(text = "Cerrar")
            }
        },
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Ninguna colección creada",
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = "Asegúrate de que tienes al menos una colección creada antes de querer eliminar un " +
                        "cuadro.",
                textAlign = TextAlign.Center
            )
        }
    )
}

@Composable
fun NotAnyCollectionsCreatedModalInArtworkAdditionOption(artworkViewModel: ArtworkViewModel) {
    AlertDialog(
        onDismissRequest = {
            artworkViewModel.onNotAnyCollectionsCreatedModalInAdditionOptionClosing()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    artworkViewModel.onNotAnyCollectionsCreatedModalInAdditionOptionClosing()
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                )
            ) {
                Text(text = "Cerrar")
            }
        },
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Ninguna colección creada",
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        },
        text = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Asegúrate de que tienes al menos una colección creada antes de querer añadir un " +
                        "cuadro.",
                textAlign = TextAlign.Center,
                color = Color.DarkGray
            )
        }
    )
}

@Composable
fun ArtworkInAllCollectionsModal(artworkViewModel: ArtworkViewModel) {
    AlertDialog(
        onDismissRequest = { artworkViewModel.onArtworkInAllCollectionsModalClosing() },
        confirmButton = {
            TextButton(
                onClick = { artworkViewModel.onArtworkInAllCollectionsModalClosing() },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                )
            ) {
                Text(text = "Cerrar")
            }
        },
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Ninguna colección libre",
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        },
        text = {
            Text(
                text = "Asegúrate de que tienes al menos una colección sin este cuadro antes de querer " +
                        "añadirlo.",
                textAlign = TextAlign.Center,
                color = Color.DarkGray
            )
        }
    )
}

@Composable
fun NotAnyArtworksInCollectionsModal(artworkViewModel: ArtworkViewModel) {
    AlertDialog(
        onDismissRequest = { artworkViewModel.onNotAnyArtworksInCollectionsModalClosing() },
        confirmButton = {
            TextButton(
                onClick = { artworkViewModel.onNotAnyArtworksInCollectionsModalClosing() },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                )
            ) {
                Text(text = "Cerrar")
            }
        },
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Cuadro no almacenado",
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        },
        text = {
            Text(
                text = "Asegúrate de que el cuadro está almacenado en al menos una colección antes de " +
                        "querer eliminarlo.",
                textAlign = TextAlign.Center,
                color = Color.DarkGray
            )
        }
    )
}

@Composable
fun ArtworkAdditionToRemainingCollectionModal(
    artworkViewModel: ArtworkViewModel,
    remainingCollectionTitle: String,
    onModalAcceptButtonClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { artworkViewModel.onArtworkAdditionToRemainingCollectionClosing() },
        confirmButton = {
            TextButton(
                onClick = { onModalAcceptButtonClick() },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                )
            ) {
                Text(text = "Aceptar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = { artworkViewModel.onArtworkAdditionToRemainingCollectionClosing() },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                )
            ) {
                Text(text = "Cerrar")
            }
        },
        title = {
            Text(
                text = "Añadir cuadro",
                color = Color.Black
            )
        },
        text = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "¿Quieres añadir el cuadro a la colección '$remainingCollectionTitle?'",
                textAlign = TextAlign.Center,
                color = Color.DarkGray
            )
        }
    )
}

@Composable
fun ArtworkDeletionFromRemainingCollectionModal(
    artworkViewModel: ArtworkViewModel,
    remainingCollectionTitle: String,
    onModalAcceptButtonClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { artworkViewModel.onArtworkDeletionFromRemainingCollectionClosing() },
        confirmButton = {
            TextButton(
                onClick = { onModalAcceptButtonClick() },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                )
            ) {
                Text(text = "Aceptar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = { artworkViewModel.onArtworkDeletionFromRemainingCollectionClosing() },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                )
            ) {
                Text(text = "Cerrar")
            }
        },
        title = {
            Text(
                text = "Eliminar cuadro",
                color = Color.Black
            )
        },
        text = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "¿Quieres eliminar el cuadro de la colección '$remainingCollectionTitle'?",
                textAlign = TextAlign.Center,
                color = Color.DarkGray
            )
        }
    )
}