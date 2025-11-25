package com.project.musapp.feature.artwork.presentation.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
            TextButton(onClick = {
                artworkViewModel.onNotAnyCollectionsCreatedModalInDeletionOptionClosing()
            }) {
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
            TextButton(onClick = {
                artworkViewModel.onNotAnyCollectionsCreatedModalInAdditionOptionClosing()
            }) {
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
                text = "Asegúrate de que tienes al menos una colección creada antes de querer añadir un " +
                        "cuadro.",
                textAlign = TextAlign.Center
            )
        }
    )
}

@Composable
fun ArtworkInAllCollectionsModal(artworkViewModel: ArtworkViewModel) {
    AlertDialog(
        onDismissRequest = { artworkViewModel.onArtworkInAllCollectionsModalClosing() },
        confirmButton = {
            TextButton(onClick = { artworkViewModel.onArtworkInAllCollectionsModalClosing() }) {
                Text(text = "Cerrar")
            }
        },
        title = {
            Text(
                text = "Ninguna colección libre",
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = "Asegúrate de que tienes al menos una colección sin este cuadro antes de querer " +
                        "añadirlo.",
                textAlign = TextAlign.Center
            )
        }
    )
}

@Composable
fun NotAnyArtworksInCollectionsModal(artworkViewModel: ArtworkViewModel) {
    AlertDialog(
        onDismissRequest = { artworkViewModel.onNotAnyArtworksInCollectionsModalClosing() },
        confirmButton = {
            TextButton(onClick = { artworkViewModel.onNotAnyArtworksInCollectionsModalClosing() }) {
                Text(text = "Cerrar")
            }
        },
        title = {
            Text(
                text = "Cuadro no almacenado",
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = "Asegúrate de que el cuadro está almacenado en al menos una colección antes de " +
                        "querer eliminarlo.",
                textAlign = TextAlign.Center
            )
        }
    )
}