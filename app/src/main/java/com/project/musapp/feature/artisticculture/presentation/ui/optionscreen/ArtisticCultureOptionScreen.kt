package com.project.musapp.feature.artisticculture.presentation.ui.optionscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.musapp.ui.commoncomponents.BoldText

@Composable
fun ArtisticCultureOptionScreen(
    onArtisticCultureTechnicalGlossaryOptionClick: () -> Unit,
    onArtisticCultureCuriosityOptionClick: () -> Unit
) {
    Scaffold(
        topBar = {
            ArtisticCultureOptionScreenTopBar()
        },
    ) { _ ->
        ArtisticCultureOptionScreenBody(
            onArtisticCultureTechnicalGlossaryOptionClick = {
                onArtisticCultureTechnicalGlossaryOptionClick()
            }
        ) {
            onArtisticCultureCuriosityOptionClick()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ArtisticCultureOptionScreenTopBar() {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Cultura artística",
                textAlign = TextAlign.Center,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(color = 0xFF12AA7A)
        )
    )
}

@Composable
private fun ArtisticCultureOptionScreenBody(
    onArtisticCultureTechnicalGlossaryOptionClick: () -> Unit,
    onArtisticCultureCuriosityOptionClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(weight = 1.5f))

        ArtisticCultureOptionCard(
            icon = {
                Icon(
                    modifier = Modifier.size(size = 70.dp),
                    imageVector = Icons.AutoMirrored.Outlined.MenuBook,
                    contentDescription = "Glosario técnico"
                )
            },
            title = "Glosario técnico",
            backgroundColor = Color(color = 0xFF58CAF7)
        ) { onArtisticCultureTechnicalGlossaryOptionClick() }

        Spacer(modifier = Modifier.weight(weight = 1f))

        ArtisticCultureOptionCard(
            icon = {
                Icon(
                    modifier = Modifier.size(size = 70.dp),
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Información de curiosidades de cuadros y pintores"
                )
            },
            title = "Curiosidades sobre cuadros y pintores",
            backgroundColor = Color(color = 0xFFCCFE44)
        ) { onArtisticCultureCuriosityOptionClick() }

        Spacer(modifier = Modifier.weight(weight = 1.5f))
    }
}

@Composable
private fun ArtisticCultureOptionCard(
    icon: @Composable () -> Unit,
    title: String,
    backgroundColor: Color,
    onArtisticCultureOptionClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .size(
                width = 260.dp,
                height = 200.dp
            ),
        shape = RoundedCornerShape(size = 10.dp),
        border = BorderStroke(width = 3.dp, color = Color.Black),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        onClick = { onArtisticCultureOptionClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = backgroundColor),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            icon()

            BoldText(
                modifier = Modifier.padding(horizontal = 2.dp),
                text = title,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                lineHeight = 30.sp
            )
        }
    }
}