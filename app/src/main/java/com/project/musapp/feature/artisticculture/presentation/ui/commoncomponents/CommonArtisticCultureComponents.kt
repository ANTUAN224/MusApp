package com.project.musapp.feature.artisticculture.presentation.ui.commoncomponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.musapp.feature.artisticculture.presentation.model.CuriosityUiModel
import com.project.musapp.feature.artisticculture.presentation.model.TermUiModel
import com.project.musapp.ui.commoncomponents.BoldText
import com.project.musapp.ui.commoncomponents.CommonVerticalSpacer
import kotlin.reflect.KClass

@Composable
fun <T : Any> CommonArtisticCultureButtonList(
    modifier: Modifier,
    content: List<T>,
    classType: KClass<T>,
    onButtonClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content.forEachIndexed { index, button ->
            item {
                if (index == 0) {
                    CommonVerticalSpacer(height = 50.dp)
                }

                OutlinedButton(
                    modifier = if (classType == TermUiModel::class) {
                        Modifier.size(width = 199.dp, height = 84.dp)
                    } else {
                        Modifier.size(width = 205.dp, height = 100.dp)
                    },
                    onClick = {
                        onButtonClick(index)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    )
                ) {
                    BoldText(
                        modifier = Modifier.fillMaxWidth(),
                        text = if (classType == TermUiModel::class) {
                            (button as TermUiModel).name
                        } else {
                            (button as CuriosityUiModel).title
                        },
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center
                    )
                }

                CommonVerticalSpacer(height = 50.dp)
            }
        }
    }
}