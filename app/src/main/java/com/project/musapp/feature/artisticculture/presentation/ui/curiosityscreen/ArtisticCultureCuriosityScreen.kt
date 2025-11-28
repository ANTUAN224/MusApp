package com.project.musapp.feature.artisticculture.presentation.ui.curiosityscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.project.musapp.feature.artisticculture.presentation.model.CuriosityUiModel
import com.project.musapp.feature.artisticculture.presentation.ui.commoncomponents.CommonArtisticCultureButtonList
import com.project.musapp.feature.artisticculture.presentation.viewmodel.ArtisticCultureViewModel
import com.project.musapp.ui.commoncomponents.CommonTopBar

@Composable
fun ArtisticCultureCuriosityScreen(
    artisticCultureViewModel: ArtisticCultureViewModel,
    onReturnToArtisticCultureOptionScreen: () -> Unit
) {
    Scaffold(
        topBar = { ArtisticCultureCuriosityTopBar { onReturnToArtisticCultureOptionScreen() } }
    ) { innerPadding ->
        ArtisticCultureCuriosityBody(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(color = 0xFFCCFE44))
                .padding(paddingValues = innerPadding),
            artisticCultureViewModel = artisticCultureViewModel
        )
    }
}

@Composable
private fun ArtisticCultureCuriosityTopBar(onReturnToArtisticCultureOptionScreen: () -> Unit) {
    CommonTopBar(title = "Curiosidades sobre cuadros y pintores") {
        onReturnToArtisticCultureOptionScreen()
    }
}

@Composable
private fun ArtisticCultureCuriosityBody(
    modifier: Modifier,
    artisticCultureViewModel: ArtisticCultureViewModel
) {
    val curiosities by artisticCultureViewModel.curiosities.observeAsState()

    CommonArtisticCultureButtonList(
        modifier = modifier,
        content = curiosities!!,
        classType = CuriosityUiModel::class
    ) { index ->
        artisticCultureViewModel.onCuriosityClick(index = index)
    }
}

@Composable
fun CuriosityDescriptionModal(artisticCultureViewModel: ArtisticCultureViewModel) {
    val selectedCuriosity by artisticCultureViewModel.selectedCuriosity.observeAsState()

    AlertDialog(
        onDismissRequest = { artisticCultureViewModel.onCuriosityDescriptionModalClosing() },
        confirmButton = {
            TextButton(
                onClick = {
                    artisticCultureViewModel.onCuriosityDescriptionModalClosing()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(color = 0xFFCCFE44),
                    contentColor = Color.Black
                )
            ) {
                Text(text = "Cerrar")
            }
        },
        title = { Text(text = "Curiosidad") },
        text = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = selectedCuriosity!!.description.replace(oldChar = '\n', newChar = ' '),
                textAlign = TextAlign.Center
            )
        },
        containerColor = Color(color = 0xFFCCFE44)
    )
}