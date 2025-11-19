package com.project.musapp.feature.collection.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CreateNewFolder
import  com.project.musapp.R
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.FolderDelete
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.project.musapp.feature.collection.presentation.model.CollectionReadingUiModel
import com.project.musapp.feature.collection.presentation.viewmodel.CollectionViewModel
import com.project.musapp.ui.commoncomponents.BoldText
import com.project.musapp.ui.commoncomponents.CommonVerticalSpacer

@Composable
fun CollectionScreen(
    collectionViewModel: CollectionViewModel,
    userCollections: List<CollectionReadingUiModel>,
    onUniqueCollectionDeletion: () -> Unit
) {
    Scaffold(topBar = {
        CollectionScreenTopBar(
            collectionViewModel = collectionViewModel,
            userCollections = userCollections
        ) {
            onUniqueCollectionDeletion()
        }
    }
    ) { innerPadding ->
        CollectionScreenBody(
            modifier = Modifier.padding(paddingValues = innerPadding),
            userCollections = userCollections
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionScreenTopBar(
    collectionViewModel: CollectionViewModel,
    userCollections: List<CollectionReadingUiModel>,
    onUniqueCollectionDeletion: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Colecciones personalizadas",
                fontSize = TextUnit(value = 18f, type = TextUnitType.Sp),
                color = Color.White
            )
        },
        actions = {
            IconButton(onClick = { collectionViewModel.onCollectionCreationModalOpening() }) {
                Icon(
                    imageVector = Icons.Outlined.CreateNewFolder,
                    contentDescription = "Creación de una nueva colección",
                    tint = Color.White
                )
            }

            IconButton(onClick = {
                if (userCollections.isNotEmpty()) {
                    if (userCollections.size == 1) {
                        collectionViewModel.onCollectionRenamingModalOpening()
                    } else {
                        collectionViewModel.onCollectionRenamingOptionModalOpening()
                    }
                } else {
                    collectionViewModel.onNotAnyCollectionsToRenameModalOpening()
                }
            }) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "Renombramiento de una colección",
                    tint = Color.White
                )
            }

            IconButton(onClick = {
                if (userCollections.isNotEmpty()) {
                    if (userCollections.size == 1) {
                        onUniqueCollectionDeletion()
                    } else {
                        collectionViewModel.onCollectionBatchDeletionModalOpening()
                    }
                } else {
                    collectionViewModel.onNotAnyCollectionsToDeleteModalOpening()
                }
            }) {
                Icon(
                    imageVector = Icons.Outlined.FolderDelete,
                    contentDescription = "Eliminación de una colección",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(color = 0xFF12AA7A))
    )
}

@Composable
fun CollectionScreenBody(
    modifier: Modifier,
    userCollections: List<CollectionReadingUiModel>
) {
    if (userCollections.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(space = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(size = 80.dp),
                    painter = painterResource(R.drawable.perm_media_24dp_e3e3e3_fill0_wght400_grad0_opsz24),
                    contentDescription = "Apartado de colecciones creadas por el usuario",
                    tint = Color.Black
                )

                BoldText(
                    text = "No hay ninguna colección creada."
                )

                BoldText(
                    text = "Puedes crear tantas colecciones como quieras. " +
                            "Si quieres tener organizados los cuadros, ¡este es tu lugar!",
                    textAlign = TextAlign.Center
                )
            }
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(userCollections) { userCollection ->
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        modifier = Modifier
                            .size(size = 250.dp)
                            .clickable {},
                        imageVector = Icons.Outlined.Folder,
                        contentDescription = "Colección creada por el usuario",
                        tint = Color.Black
                    )

                    BoldText(
                        modifier = Modifier.width(width = 150.dp),
                        text = userCollection.title,
                        textAlign = TextAlign.Center,
                        fontSize = TextUnit(value = 15f, type = TextUnitType.Sp)
                    )
                }

                CommonVerticalSpacer(height = 15.dp)
            }
        }
    }
}