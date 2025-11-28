package com.project.musapp.feature.home.presentation.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SearchBarDefaults.InputField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import com.project.musapp.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.project.musapp.ui.commoncomponents.BoldText
import com.project.musapp.feature.user.presentation.model.UserProfileUiModel
import com.project.musapp.feature.home.presentation.viewmodel.HomeViewModel
import com.project.musapp.ui.commoncomponents.CommonArtworkPreviewList
import com.project.musapp.ui.commoncomponents.CommonWallArtIcon
import com.project.musapp.ui.commoncomponents.UserProfileImage

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    userProfile: UserProfileUiModel,
    applicationContext: Context,
    onNavBarHiding: () -> Unit,
    onNavBarShowing: () -> Unit,
    onArtworkPreviewClick: (Long) -> Unit,
    onReturnToInitialMenu: () -> Unit,
) {
    val isSearching by homeViewModel.isSearching.observeAsState(initial = false)

    AnimatedContent(
        targetState = isSearching
    ) { isSearching ->

        if (!isSearching) {
            LaunchedEffect(Unit) {
                onNavBarShowing()
            }

            Scaffold(
                topBar = {
                    HomeScreenTopBar(
                        homeViewModel = homeViewModel,
                        userProfile = userProfile,
                        applicationContext = applicationContext
                    ) {
                        onReturnToInitialMenu()
                    }
                }) { innerPadding ->
                HomeScreenBody(
                    modifier = Modifier.padding(paddingValues = innerPadding),
                    homeViewModel = homeViewModel,
                    onArtworkPreviewClick = onArtworkPreviewClick
                )
            }
        } else {
            LaunchedEffect(Unit) {
                onNavBarHiding()
            }

            HomeScreenSearchBar(
                homeViewModel = homeViewModel,
                onArtworkPreviewClick = onArtworkPreviewClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenTopBar(
    homeViewModel: HomeViewModel,
    userProfile: UserProfileUiModel,
    applicationContext: Context,
    onReturnToInitialMenu: () -> Unit
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
            IconButton(onClick = {
                homeViewModel.onSearchBarStateChange(
                    isSearching = true,
                    hasSearchKeyBeenPressed = false
                )
            }) {
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
                            userProfileImageUri = userProfile.profileImageUrl, size = 24.dp
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
                    DropdownMenuItem(
                        text = { Text("Tema") },
                        trailingIcon = {
                            Switch(
                                enabled = false,
                                checked = isDarkModeActivated,
                                onCheckedChange = { homeViewModel.onSwitchClick(it) },
                                thumbContent = {
                                    Icon(
                                        imageVector =
                                            if (isDarkModeActivated) Icons.Filled.DarkMode
                                            else Icons.Filled.LightMode,
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
                        },
                        onClick = {
                            Toast.makeText(
                                applicationContext,
                                "¡Próximamente podrás cambiar el tema de la app!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenSearchBar(
    homeViewModel: HomeViewModel,
    onArtworkPreviewClick: (Long) -> Unit
) {
    val query by homeViewModel.query.observeAsState()
    val searchArtworks by homeViewModel.searchArtworks.observeAsState()
    val hasSearchKeyBeenPressed by homeViewModel.hasSearchKeyBeenPressed.observeAsState(initial = false)
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    SearchBar(
        inputField = {
            InputField(
                modifier = Modifier.focusRequester(focusRequester = focusRequester),
                leadingIcon = {
                    IconButton(onClick = {
                        homeViewModel.onSearchBarStateChange(
                            isSearching = false,
                            hasSearchKeyBeenPressed = hasSearchKeyBeenPressed
                        )

                        focusManager.clearFocus(force = true)
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Vuelta a la pantalla principal"
                        )
                    }
                },
                query = query!!,
                onQueryChange = { homeViewModel.onQueryChange(currentQuery = it) },
                onSearch = {
                    homeViewModel.onSearchKeyPressedStateChange(hasSearchKeyBeenPressed = true)

                    focusManager.clearFocus(force = true)
                },
                placeholder = { Text(text = "Busca el cuadro por título o autor") },
                expanded = true,
                onExpandedChange = {
                    homeViewModel.onSearchBarStateChange(
                        isSearching = it,
                        hasSearchKeyBeenPressed = hasSearchKeyBeenPressed
                    )
                    if (hasSearchKeyBeenPressed) {
                        homeViewModel.onSearchKeyPressedStateChange(hasSearchKeyBeenPressed = false)
                    }
                }
            )
        },
        expanded = true,
        onExpandedChange = {
            homeViewModel.onSearchBarStateChange(
                isSearching = it,
                hasSearchKeyBeenPressed = hasSearchKeyBeenPressed
            )

            if (hasSearchKeyBeenPressed) {
                homeViewModel.onSearchKeyPressedStateChange(hasSearchKeyBeenPressed = false)
            }
        },
        colors = SearchBarDefaults.colors(
            containerColor = Color.White
        )
    ) {
        LazyColumn {
            items(searchArtworks!!) { searchArtwork ->
                if (searchArtwork.title.contains(
                        other = query!!.trim(),
                        ignoreCase = true
                    ) || searchArtwork.authorHistoricallyKnownName.contains(
                        other = query!!.trim(),
                        ignoreCase = true
                    )
                ) {
                    ListItem(
                        modifier = Modifier.clickable {
                            focusManager.clearFocus(force = true)

                            onArtworkPreviewClick(searchArtwork.id)

                            if (hasSearchKeyBeenPressed) {
                                homeViewModel.onSearchKeyPressedStateChange(
                                    hasSearchKeyBeenPressed = false
                                )
                            }
                        },
                        leadingContent = {
                            AsyncImage(
                                modifier = Modifier.size(size = 60.dp),
                                model = searchArtwork.imageUrl,
                                contentDescription = "Cuadro de ${searchArtwork.title}"
                            )
                        },
                        headlineContent = {
                            Text(
                                text = searchArtwork.title,
                                color = Color.Black
                            )
                                          },
                        supportingContent = {
                            Text(
                                text = searchArtwork.authorHistoricallyKnownName,
                                color = Color.DarkGray
                            )
                                            },
                        colors = ListItemDefaults.colors(
                            containerColor = Color.White
                        ),
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeScreenBody(
    modifier: Modifier,
    homeViewModel: HomeViewModel,
    onArtworkPreviewClick: (Long) -> Unit
) {
    val userFavoriteArtworks by homeViewModel.userFavoriteArtworks.observeAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 65.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            BoldText(
                text = "Tus cuadros favoritos",
                fontSize = 20.sp
            )
        }

        if (userFavoriteArtworks!!.isEmpty()) {
            Spacer(modifier = Modifier.weight(weight = 0.5f))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(space = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CommonWallArtIcon()

                BoldText(
                    text = "Todavía no tienes ningún cuadro favorito.",
                    textAlign = TextAlign.Center
                )

                BoldText(
                    text = "¡Comienza a explorar obras de arte y descubre aquí tus favoritas!",
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.weight(weight = 1f))
        } else {
            CommonArtworkPreviewList(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(weight = 1f)
                    .padding(bottom = 80.dp),
                artworkPreviewList = userFavoriteArtworks!!,
            ) { artworkId ->
                onArtworkPreviewClick(artworkId)
            }
        }
    }
}