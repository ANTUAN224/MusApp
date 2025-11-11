package com.project.musapp.ui.commoncomponents

import android.net.Uri
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.project.musapp.feature.artwork.presentation.model.artwork.ArtworkPreviewUiModel

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
        text = { Text(text = "Actualmente, no tienes acceso a Internet, por lo que no podrás acceder a la app. Verifica tu conexión y vuelva a intentarlo en otra ocasión.") })
}

@Composable
fun BoldText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign? = null
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = fontSize,
        textAlign = textAlign,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun CommonVerticalSpacer(height: Dp) {
    Spacer(modifier = Modifier.height(height = height))
}

@Composable
fun CommonHorizontalSpacer() {
    Spacer(modifier = Modifier.width(15.dp))
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
fun ArtworkPreviewCard(
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
            text = userFavoriteArtworkPreview.authorHistoricallyKnownName
        )
    }
}