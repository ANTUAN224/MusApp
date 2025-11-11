package com.project.musapp.feature.home.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.project.musapp.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.project.musapp.ui.commoncomponents.BoldText
import com.project.musapp.ui.commoncomponents.CommonVerticalSpacer
import com.project.musapp.feature.user.presentation.model.UserProfileUiModel
import com.project.musapp.feature.artwork.presentation.model.artwork.chunkInPairs
import com.project.musapp.feature.home.presentation.viewmodel.HomeViewModel
import com.project.musapp.ui.commoncomponents.ArtworkPreviewCard
import com.project.musapp.ui.commoncomponents.CommonHorizontalSpacer
import com.project.musapp.ui.commoncomponents.UserProfileImage

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onArtworkPreviewClick: (Long) -> Unit,
    onReturnToInitialMenu: () -> Unit
) {
    val userProfile by homeViewModel.userProfile.observeAsState()

    Scaffold(
        topBar = {
            HomeScreenTopBar(homeViewModel = homeViewModel, userProfile = userProfile!!) {
                onReturnToInitialMenu()
            }
        }) { innerPadding ->
        HomeScreenBody(
            modifier = Modifier.padding(paddingValues = innerPadding),
            homeViewModel = homeViewModel,
            userProfile = userProfile!!,
            onArtworkPreviewClick = onArtworkPreviewClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar(
    homeViewModel: HomeViewModel, userProfile: UserProfileUiModel, onReturnToInitialMenu: () -> Unit
) {
    val isFirstDropdownMenuExpanded by homeViewModel.isFirstDropdownMenuExpanded.observeAsState(
        initial = false
    )
    val isSettingDropdownMenuExpanded by homeViewModel.isSettingDropdownMenuExpanded.observeAsState(
        initial = false
    )
    val isProfileDropdownMenuExpanded by homeViewModel.isProfileDropdownMenuExpanded.observeAsState(
        initial = false
    )
    val isDarkModeActivated by homeViewModel.isDarkModeActivated.observeAsState(initial = false)

    TopAppBar(
        title = { Text("MusApp", color = Color.White) }, actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Buscar cuadros por título, autor o época cultural",
                    tint = Color.White
                )
            }

            Box {
                IconButton(onClick = { homeViewModel.onFirstDropdownMenuExpansion() }) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "Opciones de ajustes de la app y cuenta del usuario",
                        tint = Color.White
                    )
                }

                DropdownMenu(
                    expanded = isFirstDropdownMenuExpanded,
                    onDismissRequest = { homeViewModel.onFirstDropdownMenuCollapse() }) {
                    DropdownMenuItem(leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.settings_24px),
                            contentDescription = "Ajustes",
                            tint = Color.Black
                        )
                    }, text = { Text("Ajustes") }, trailingIcon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowRight,
                            contentDescription = "Acceso al submenú de ajustes",
                            tint = Color.Black
                        )
                    }, onClick = { homeViewModel.onSettingDropdownMenuExpansion() })

                    DropdownMenuItem(leadingIcon = {
                        UserProfileImage(
                            userProfileImageUri = userProfile.profileImageUrl, size = 30.dp
                        )
                    }, text = { Text("Cuenta") }, trailingIcon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowRight,
                            contentDescription = "Acceso al submenú de cuenta del usuario",
                            tint = Color.Black
                        )
                    }, onClick = { homeViewModel.onUserProfileDropdownMenuExpansion() })
                }

                DropdownMenu(
                    expanded = isSettingDropdownMenuExpanded,
                    onDismissRequest = { homeViewModel.onSettingDropdownMenuCollapse() }) {
                    DropdownMenuItem(text = { Text("Tema") }, trailingIcon = {
                        Switch(
                            checked = isDarkModeActivated,
                            onCheckedChange = { homeViewModel.onSwitchClick(it) },
                            thumbContent = {
                                Icon(
                                    imageVector = if (isDarkModeActivated) Icons.Filled.DarkMode else Icons.Filled.LightMode,
                                    contentDescription = "Tema de la app"
                                )
                            },
                            colors = SwitchDefaults.colors(
                                checkedBorderColor = Color.Black,
                                uncheckedBorderColor = Color.Black,
                                checkedTrackColor = Color.Black,
                                uncheckedTrackColor = Color.Black,
                                checkedThumbColor = Color.White,
                                uncheckedThumbColor = Color.White,
                                checkedIconColor = Color.Black,
                                uncheckedIconColor = Color.Black
                            )
                        )
                    }, onClick = { homeViewModel.onSwitchClick(!isDarkModeActivated) })

                    DropdownMenuItem(text = { Text("Ayuda") }, trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.contact_support_24px),
                            contentDescription = "Contacto con el soporte",
                            tint = Color.Black
                        )
                    }, onClick = { homeViewModel.onContactWithSupportModalOpening() })
                }

                DropdownMenu(
                    expanded = isProfileDropdownMenuExpanded,
                    onDismissRequest = { homeViewModel.onUserProfileDropdownMenuCollapse() }) {
                    DropdownMenuItem(text = { Text("Mostrar perfil") }, trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.article_person_24px),
                            contentDescription = "Información del perfil de usuario",
                            tint = Color.Black
                        )
                    }, onClick = { homeViewModel.onUserProfileModalOpening() })

                    DropdownMenuItem(text = { Text("Cerrar sesión") }, trailingIcon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Logout,
                            contentDescription = "Cierre de sesión del usuario",
                            tint = Color.Black
                        )
                    }, onClick = { onReturnToInitialMenu() })
                }
            }
        }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(color = 0xFF12AA7A))
    )
}

@Composable
fun HomeScreenBody(
    modifier: Modifier,
    homeViewModel: HomeViewModel,
    userProfile: UserProfileUiModel,
    onArtworkPreviewClick: (Long) -> Unit
) {
    val showSupportContactModal by homeViewModel.showContactWithSupportModal.observeAsState(initial = false)
    val showUserProfileModal by homeViewModel.showUserProfileModal.observeAsState(initial = false)
    val userFavoriteArtworkPreviews by homeViewModel.userFavoriteArtworks.observeAsState()

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 15.dp)
    ) {
        val (bodyTitle, bodyContent) = createRefs()

        BoldText(
            modifier = Modifier
                .constrainAs(ref = bodyTitle) {
                    top.linkTo(anchor = parent.top)
                    bottom.linkTo(anchor = parent.bottom)
                    verticalBias = 0.01f
                }
                .padding(vertical = 30.dp),
            text = "Tus cuadros favoritos", fontSize = 20.sp
        )

        if (userFavoriteArtworkPreviews!!.isEmpty()) {
            Column(
                modifier = Modifier
                    .constrainAs(ref = bodyContent) {
                        top.linkTo(anchor = bodyTitle.bottom)
                        bottom.linkTo(anchor = parent.bottom)
                        start.linkTo(anchor = parent.start)
                        end.linkTo(anchor = parent.end)
                        verticalBias = 0.35f
                    }
                    .padding(horizontal = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    modifier = Modifier.size(size = 80.dp),
                    painter = painterResource(R.drawable.wall_art_24px),
                    contentDescription = "Cuadro pegado en la pared",
                    tint = Color.Black
                )

                CommonVerticalSpacer(height = 23.dp)

                BoldText(text = "Todavía no tienes ningún cuadro favorito.")

                CommonVerticalSpacer(height = 23.dp)

                BoldText(
                    text = "¡Comienza a explorar obras de arte y descubre aquí tus favoritas!",
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyColumn(
                modifier = modifier
                    .constrainAs(ref = bodyContent) {
                        top.linkTo(anchor = parent.top)
                        bottom.linkTo(anchor = parent.bottom)
                        verticalBias = 0f
                    }
                    .fillMaxHeight()
                    .padding(bottom = 80.dp)) {
                items(userFavoriteArtworkPreviews!!.chunkInPairs())
                { (firstArtworkPreview, secondArtworkPreview) ->
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
            }
        }
    }

    if (showSupportContactModal) {
        ContactWithSupportModal(homeViewModel = homeViewModel)
    }

    if (showUserProfileModal) {
        UserProfileModal(userProfile = userProfile, homeViewModel = homeViewModel)
    }
}