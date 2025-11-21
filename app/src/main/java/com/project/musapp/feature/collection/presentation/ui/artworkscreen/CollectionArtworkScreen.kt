package com.project.musapp.feature.collection.presentation.ui.artworkscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.musapp.feature.artwork.presentation.model.artwork.chunkInPairs
import com.project.musapp.ui.commoncomponents.ArtworkPreviewRowItem
import com.project.musapp.ui.commoncomponents.BoldText
import com.project.musapp.R
import com.project.musapp.feature.artwork.presentation.model.artwork.ArtworkPreviewUiModel
import com.project.musapp.ui.commoncomponents.CommonVerticalSpacer

@Composable
fun CollectionArtworkScreen(
    collectionTitle: String,
    collectionArtworks: List<ArtworkPreviewUiModel>,
    onCollectionPreviewScreenReturn: () -> Unit,
    onArtworkPreviewClick: (Long) -> Unit
) {
    Scaffold(topBar = {
        CollectionArtworkScreenTopBar {
            onCollectionPreviewScreenReturn()
        }
    }
    ) { innerPadding ->
        CollectionArtworkScreenBody(
            modifier = Modifier.padding(paddingValues = innerPadding),
            collectionTitle = collectionTitle,
            collectionArtworks = collectionArtworks
        ) { artworkId ->
            onArtworkPreviewClick(artworkId)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CollectionArtworkScreenTopBar(onCollectionPreviewScreenReturn: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { onCollectionPreviewScreenReturn() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Regreso a la pantalla de las colecciones del usuario.",
                    tint = Color.White
                )
            }
        },
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Colecciones personalizadas",
                color = Color.White,
                textAlign = TextAlign.Center
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(color = 0xFF12AA7A))
    )
}

@Composable
private fun CollectionArtworkScreenBody(
    modifier: Modifier,
    collectionTitle: String,
    collectionArtworks: List<ArtworkPreviewUiModel>,
    onArtworkPreviewClick: (Long) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BoldText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            text = collectionTitle,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        CommonVerticalSpacer(height = 20.dp)

        HorizontalDivider(
            thickness = 2.dp,
            color = Color.LightGray
        )

        if (collectionArtworks.isEmpty()) {
            Spacer(modifier = Modifier.weight(weight = 0.5f))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(space = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(size = 80.dp),
                    painter = painterResource(R.drawable.wall_art_24px),
                    contentDescription = "Cuadro pegado en la pared"
                )

                BoldText(
                    text = "No hay ningún cuadro en esta colección.",
                    textAlign = TextAlign.Center
                )

                BoldText(
                    text = "Aquí puedes almacenar los cuadros que quieras. ¡Aprovéchalo!",
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.weight(weight = 1f))
        } else {
            Spacer(modifier = Modifier.weight(weight = 0.05f))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(weight = 0.95f)
            ) {
                items(collectionArtworks.chunkInPairs())
                { (firstArtworkPreview, secondArtworkPreview) ->
                    ArtworkPreviewRowItem(
                        firstArtworkPreview = firstArtworkPreview,
                        secondArtworkPreview = secondArtworkPreview
                    ) { artworkId ->
                        onArtworkPreviewClick(artworkId)
                    }
                }
            }
        }
    }
}