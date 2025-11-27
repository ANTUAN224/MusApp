package com.project.musapp.feature.artisticculture.presentation.ui.technicalglossaryscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.project.musapp.feature.artisticculture.presentation.model.TermUiModel
import com.project.musapp.feature.artisticculture.presentation.ui.commoncomponents.CommonArtisticCultureButtonList
import com.project.musapp.feature.artisticculture.presentation.viewmodel.ArtisticCultureViewModel
import com.project.musapp.ui.commoncomponents.CommonTopBar

@Composable
fun ArtisticCultureTechnicalGlossaryScreen(
    artisticCultureViewModel: ArtisticCultureViewModel,
    onReturnToArtisticCultureOptionScreen: () -> Unit
) {
    Scaffold(
        topBar = {
            ArtisticCultureTechnicalGlossaryTopBar { onReturnToArtisticCultureOptionScreen() }
        }
    ) { innerPadding ->
        ArtisticCultureTechnicalGlossaryBody(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(color = 0xFF58CAF7))
                .padding(paddingValues = innerPadding)
                .padding(top = 50.dp),
            artisticCultureViewModel = artisticCultureViewModel
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ArtisticCultureTechnicalGlossaryTopBar(onReturnToArtisticCultureOptionScreen: () -> Unit) {
    CommonTopBar(title = "Glosario técnico") { onReturnToArtisticCultureOptionScreen() }
}

@Composable
private fun ArtisticCultureTechnicalGlossaryBody(
    modifier: Modifier,
    artisticCultureViewModel: ArtisticCultureViewModel
) {
    val technicalGlossary by artisticCultureViewModel.technicalGlossary.observeAsState()

    CommonArtisticCultureButtonList(
        modifier = modifier,
        content = technicalGlossary!!,
        classType = TermUiModel::class
    ) { index ->
        artisticCultureViewModel.onTermClick(index = index)
    }
}

@Composable
fun TermDefinitionModal(artisticCultureViewModel: ArtisticCultureViewModel) {
    val selectedTerm by artisticCultureViewModel.selectedTerm.observeAsState()

    AlertDialog(
        onDismissRequest = { artisticCultureViewModel.onTermDefinitionModalClosing() },
        confirmButton = {
            TextButton(
                onClick = {
                    artisticCultureViewModel.onTermDefinitionModalClosing()
                }
            ) {
                Text(text = "Cerrar", color = Color.Black)
            }
        },
        title = { Text(text = selectedTerm!!.name) },
        text = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = selectedTerm!!.definition.replace(oldChar = '\n', newChar = ' '),
                textAlign = TextAlign.Center
            )
        },
        containerColor = Color(color = 0xFF58CAF7)
    )
}