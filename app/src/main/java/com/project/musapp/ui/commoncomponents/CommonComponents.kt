package com.project.musapp.ui.commoncomponents

import android.net.Uri
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.project.musapp.R
import com.project.musapp.feature.artwork.presentation.model.artwork.ArtworkPreviewUiModel
import com.project.musapp.feature.artwork.presentation.model.artwork.chunkInPairs
import com.project.musapp.feature.collection.presentation.model.CollectionReadingUiModel

@Composable
fun CommonLoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            modifier = Modifier.size(60.dp),
            color = Color(color = 0xFF12AA7A),
            trackColor = Color.LightGray,
            strokeWidth = 5.dp
        )
    }
}

@Composable
fun CommonNoInternetConnectionModal() {
    val activity = LocalActivity.current!!
    AlertDialog(
        onDismissRequest = { activity.finish() },
        confirmButton = { TextButton(onClick = { activity.finish() }) { Text(text = "Cerrar") } },
        title = { Text(text = "Sin conexión a Internet") },
        text = {
            Text(
                text = "Actualmente, no tienes acceso a Internet, por lo que no podrás acceder a la app. Verifica tu " +
                        "conexión y vuelva a intentarlo en otra ocasión."
            )
        }
    )
}

@Composable
fun BoldText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = fontSize,
        textAlign = textAlign,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        lineHeight = lineHeight
    )
}

@Composable
fun CommonVerticalSpacer(height: Dp) {
    Spacer(modifier = Modifier.height(height = height))
}

@Composable
fun UserProfileImage(userProfileImageUri: Uri, size: Dp) {
    AsyncImage(
        modifier = Modifier
            .size(size)
            .clip(CircleShape),
        model = userProfileImageUri,
        contentDescription = "Imagen del perfil del usuario"
    )
}

@Composable
fun CommonHorizontalSpacer() {
    Spacer(modifier = Modifier.width(width = 15.dp))
}

@Composable
fun CommonArtworkPreviewList(
    modifier: Modifier,
    artworkPreviewList: List<ArtworkPreviewUiModel>,
    onArtworkPreviewClick: (Long) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(artworkPreviewList.chunkInPairs())
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

@Composable
private fun ArtworkPreviewRowItem(
    firstArtworkPreview: ArtworkPreviewUiModel,
    secondArtworkPreview: ArtworkPreviewUiModel?,
    onArtworkPreviewClick: (Long) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        ArtworkPreviewCard(
            modifier = Modifier
                .weight(1f),
            userFavoriteArtworkPreview = firstArtworkPreview
        ) {
            onArtworkPreviewClick(firstArtworkPreview.id)
        }

        CommonHorizontalSpacer()

        if (secondArtworkPreview != null) {
            ArtworkPreviewCard(
                modifier = Modifier
                    .weight(1f),
                userFavoriteArtworkPreview = secondArtworkPreview
            ) {
                onArtworkPreviewClick(secondArtworkPreview.id)
            }
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }
    }

    CommonVerticalSpacer(height = 23.dp)
}

@Composable
private fun ArtworkPreviewCard(
    modifier: Modifier,
    userFavoriteArtworkPreview: ArtworkPreviewUiModel,
    onArtworkPreviewCardClick: () -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(
            width = 2.dp, color = Color.Black
        ),
        onClick = { onArtworkPreviewCardClick() }
    ) {
        AsyncImage(
            modifier = Modifier
                .height(height = 120.dp)
                .fillMaxWidth(),
            model = userFavoriteArtworkPreview.imageUrl,
            contentDescription = "Imagen del cuadro ${userFavoriteArtworkPreview.title}",
        )

        Spacer(modifier = Modifier.weight(1f))

        BoldText(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .padding(top = 18.dp),
            text = userFavoriteArtworkPreview.title
        )

        Text(
            modifier = Modifier.padding(horizontal = 5.dp, vertical = 3.dp),
            text = userFavoriteArtworkPreview.authorHistoricallyKnownName,
            color = Color.Black
        )
    }
}

@Composable
fun CommonWallArtIcon() {
    Icon(
        modifier = Modifier.size(size = 80.dp),
        painter = painterResource(R.drawable.wall_art_24px),
        contentDescription = "Cuadro pegado en la pared"
    )
}

@Composable
fun CommonCollectionOptionMultiSelectionModal(
    title: String,
    text: String,
    collectionList: List<CollectionReadingUiModel>,
    checkedCollectionOptionIndexes: List<Int>,
    onCollectionOptionCheckedChange: (Int, Boolean) -> Unit,
    onModalCancelButtonClick: () -> Unit,
    onModalAcceptButtonClick: () -> Unit
) {
    AlertDialog(
        modifier = Modifier.height(height = 400.dp),
        onDismissRequest = { onModalCancelButtonClick() },
        title = {
            Text(
                text = title,
                color = Color.Black
            )
                },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(space = 10.dp)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = text,
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray
                )

                LazyColumn(modifier = Modifier.weight(weight = 1f)) {
                    collectionList.forEachIndexed { index, collectionOption ->
                        item {
                            ListItem(
                                modifier = Modifier.clickable {
                                    onCollectionOptionCheckedChange(
                                        index,
                                        !checkedCollectionOptionIndexes.contains(
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
                                    Text(
                                        text = collectionOption.title,
                                        color = Color.Black
                                    )
                                },

                                trailingContent = {
                                    Checkbox(
                                        checked = checkedCollectionOptionIndexes.contains(
                                            index
                                        ),
                                        onCheckedChange = {
                                            onCollectionOptionCheckedChange(
                                                index,
                                                it
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

                            if (index < collectionList.size - 1) {
                                HorizontalDivider(color = Color.LightGray)
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onModalAcceptButtonClick() },
                enabled = checkedCollectionOptionIndexes.isNotEmpty(),
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
                onClick = { onModalCancelButtonClick() },
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTopBar(title: String, onReturnToLastScreen: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { onReturnToLastScreen() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Regreso a la pantalla anterior.",
                    tint = Color.White
                )
            }
        },
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(color = 0xFF12AA7A))
    )
}