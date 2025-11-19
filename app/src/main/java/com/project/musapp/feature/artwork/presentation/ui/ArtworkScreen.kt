package com.project.musapp.feature.artwork.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import com.project.musapp.R
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.project.musapp.feature.artwork.domain.model.artistictrend.ArtisticTrendType
import com.project.musapp.feature.artwork.domain.model.author.Sex
import com.project.musapp.feature.artwork.presentation.model.artwork.ArtworkUiModel
import com.project.musapp.feature.artwork.presentation.viewmodel.ArtworkViewModel
import com.project.musapp.ui.commoncomponents.BoldText
import com.project.musapp.ui.commoncomponents.CommonHorizontalSpacer
import com.project.musapp.ui.commoncomponents.CommonVerticalSpacer
import java.util.Locale

@Composable
fun ArtworkScreen(
    artworkViewModel: ArtworkViewModel,
    hasArtworkBeenMarkedAsFavorite: Boolean,
    artwork: ArtworkUiModel,
    onReturnToHome: () -> Unit,
) {
    Scaffold(
        topBar = {
            ArtworkScreenTopBar(
                artworkViewModel = artworkViewModel,
                hasArtworkBeenMarkedAsFavorite = hasArtworkBeenMarkedAsFavorite
            ) { onReturnToHome() }
        }
    ) { innerPadding ->
        ArtworkScreenBody(
            modifier = Modifier.padding(paddingValues = innerPadding),
            artwork = artwork
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtworkScreenTopBar(
    artworkViewModel: ArtworkViewModel,
    hasArtworkBeenMarkedAsFavorite: Boolean,
    onReturnToHome: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "Información", color = Color.White) },
        navigationIcon = {
            IconButton(onClick = { onReturnToHome() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Vuelta a la pantalla de Home",
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = {
                artworkViewModel.onArtworkFavoriteIconClick(markedState = !hasArtworkBeenMarkedAsFavorite)
            }) {
                Icon(
                    imageVector =
                        if (hasArtworkBeenMarkedAsFavorite)
                            Icons.Filled.Favorite
                        else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Marcación como cuadro favorito",
                    tint = Color.White
                )
            }

            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Adición del cuadro a una o varias colecciones del usuario",
                    tint = Color.White
                )
            }

            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(R.drawable.delete_24px),
                    contentDescription = "Eliminación del cuadro de una o varias colecciones del usuario",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(color = 0xFF12AA7A))
    )
}

@Composable
fun ArtworkScreenBody(modifier: Modifier, artwork: ArtworkUiModel) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 15.dp)
            .padding(top = 20.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(intrinsicSize = IntrinsicSize.Max)
            ) {
                Column(
                    modifier = Modifier
                        .weight(weight = 1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .padding(top = 60.dp)
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(size = 10.dp)),
                        model = artwork.imageUrl,
                        contentDescription = "Imagen del cuadro ${artwork.title}"
                    )

                    Spacer(modifier = Modifier.weight(weight = 2f))

                    if (artwork.location.city != null) {
                        ArtworkInformationField(
                            title = "Ubicación actual",
                            firstContent = artwork.location.name,
                            lastContent = "${artwork.location.city}, ${artwork.location.country}"
                        )
                    } else {
                        ArtworkInformationField(
                            title = "Ubicación actual",
                            firstContent = artwork.location.name
                        )
                    }

                    Spacer(modifier = Modifier.weight(weight = 1f))
                }

                CommonHorizontalSpacer()

                Column(
                    modifier = Modifier
                        .weight(weight = 1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ArtworkInformationField(
                        title = "Título",
                        firstContent = artwork.title,
                        fontStyle = FontStyle.Italic
                    )

                    ArtworkInformationField(
                        title = if (artwork.author.sex == Sex.MALE) "Autor" else "Autora",
                        firstContent = artwork.author.historicallyKnownName
                    )

                    ArtworkInformationField(
                        title = "Época cultural",
                        firstContent = artwork.culturalEra
                    )

                    ArtworkInformationField(
                        title =
                            if (artwork.artisticTrend.type == ArtisticTrendType.ARTISTIC_MOVEMENT)
                                "Movimiento artístico"
                            else "Etapa artística",
                        firstContent = artwork.artisticTrend.name,
                        lastContent = artwork.artisticTrend.centuryRange
                    )

                    ArtworkInformationField(
                        title = "Técnica y soporte",
                        firstContent = "${artwork.technique} sobre ${
                            artwork.support.lowercase(
                                Locale("es", "ES")
                            )
                        }"
                    )

                    ArtworkInformationField(
                        title = "Dimensiones",
                        firstContent = artwork.dimensions
                    )
                }
            }
        }

        items(
            listOf(
                Pair("Descripción", artwork.description),
                Pair("Contexto histórico", artwork.historicalContext)
            )
        ) { (title, firstContent) ->
            ArtworkInformationField(
                title = title,
                firstContent = firstContent
            )
        }
    }
}

@Composable
private fun ArtworkInformationField(
    title: String,
    firstContent: String,
    lastContent: String? = null,
    fontStyle: FontStyle? = null
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        BoldText(text = title)

        Spacer(modifier = Modifier.height(height = 2.dp))

        Text(
            text = firstContent,
            fontStyle = fontStyle,
            textAlign = TextAlign.Center
        )

        if (lastContent != null) {
            Text(
                text = "($lastContent)",
                textAlign = TextAlign.Center
            )
        }

        CommonVerticalSpacer(height = 20.dp)
    }
}