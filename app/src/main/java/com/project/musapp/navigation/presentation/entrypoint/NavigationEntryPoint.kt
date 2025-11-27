package com.project.musapp.navigation.presentation.entrypoint

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.project.musapp.feature.artwork.presentation.ui.ArtworkAdditionToCollectionsModal
import com.project.musapp.feature.artwork.presentation.ui.ArtworkDeletionFromCollectionsModal
import com.project.musapp.feature.artwork.presentation.ui.ArtworkInAllCollectionsModal
import com.project.musapp.feature.artwork.presentation.ui.ArtworkScreen
import com.project.musapp.feature.artwork.presentation.ui.NotAnyArtworksInCollectionsModal
import com.project.musapp.feature.artwork.presentation.ui.NotAnyCollectionsCreatedModalInArtworkAdditionOption
import com.project.musapp.feature.artwork.presentation.ui.NotAnyCollectionsCreatedModalInArtworkDeletionOption
import com.project.musapp.feature.artwork.presentation.viewmodel.ArtworkViewModel
import com.project.musapp.feature.auth.presentation.login.ui.LoginModal
import com.project.musapp.feature.auth.presentation.login.ui.UserNotFoundModal
import com.project.musapp.feature.auth.presentation.login.viewmodel.UserLoginViewModel
import com.project.musapp.feature.auth.presentation.registration.ui.EmailAlreadyInUseModal
import com.project.musapp.feature.auth.presentation.registration.ui.UserRegistrationScreen
import com.project.musapp.feature.auth.presentation.registration.viewmodel.UserRegistrationViewModel
import com.project.musapp.feature.collection.presentation.ui.artworkscreen.CollectionArtworkScreen
import com.project.musapp.feature.collection.presentation.ui.previewscreen.CollectionBatchDeletionModal
import com.project.musapp.feature.collection.presentation.ui.previewscreen.CollectionCreationModal
import com.project.musapp.feature.collection.presentation.ui.previewscreen.CollectionRenamingModal
import com.project.musapp.feature.collection.presentation.ui.previewscreen.CollectionRenamingOptionModal
import com.project.musapp.feature.collection.presentation.ui.previewscreen.CollectionPreviewScreen
import com.project.musapp.feature.collection.presentation.ui.previewscreen.NotAnyCollectionsToDeleteModal
import com.project.musapp.feature.collection.presentation.ui.previewscreen.NotAnyCollectionsToRenameModal
import com.project.musapp.feature.collection.presentation.ui.previewscreen.UniqueCollectionToDeleteModal
import com.project.musapp.feature.collection.presentation.viewmodel.CollectionViewModel
import com.project.musapp.feature.home.presentation.ui.ContactWithSupportModal
import com.project.musapp.feature.home.presentation.ui.HomeScreen
import com.project.musapp.feature.home.presentation.ui.UserProfileModal
import com.project.musapp.feature.home.presentation.viewmodel.HomeViewModel
import com.project.musapp.navigation.presentation.initialmenu.ui.InitialMenuScreen
import com.project.musapp.navigation.presentation.navigationbar.ui.MusAppNavigationBar
import com.project.musapp.navigation.presentation.navigationbar.utils.navigateBetweenNavItems
import com.project.musapp.navigation.presentation.navigationbar.viewmodel.NavigationViewModel
import com.project.musapp.navigation.presentation.splashscreen.viewModel.SplashScreenViewModel
import com.project.musapp.navigation.routing.RouteHub
import com.project.musapp.ui.commoncomponents.CommonLoadingScreen
import com.project.musapp.ui.commoncomponents.CommonNoInternetConnectionModal

@Composable
fun NavigationEntryPoint(applicationContext: Context) {
    val navigationViewModel: NavigationViewModel = hiltViewModel()

    val navController = rememberNavController()

    val showNavBar by
    navigationViewModel.showNavBar.observeAsState(initial = false)

    val currentNavItemIndex by
    navigationViewModel.navItemIndex.observeAsState(initial = 0)

    val isArrivingForFirstTimeToCollection by
    navigationViewModel.isArrivingForFirstTimeToCollection.observeAsState(initial = true)

    val isArrivingForFirstTimeToArtisticCulture by
    navigationViewModel.isArrivingForFirstTimeToArtisticCulture.observeAsState(initial = true)

    val hasArtworkBeenNavigatedFromCollection by
    navigationViewModel.hasArtworkBeenNavigatedFromCollection.observeAsState(initial = false)

    val hasArtworkBeenAddedToCollections by
    navigationViewModel.hasArtworkBeenAddedToCollections.observeAsState(initial = false)

    val hasArtworkBeenDeletedFromCollections by
    navigationViewModel.hasArtworkBeenDeletedFromCollections.observeAsState(initial = false)

    val hasArtworkBeenMarkedAsFavorite by
    navigationViewModel.hasArtworkBeenMarkedAsFavorite.observeAsState(initial = null)

    Scaffold(bottomBar = {
        if (showNavBar) {
            MusAppNavigationBar(
                navigationViewModel = navigationViewModel,
                currentNavItemIndex = currentNavItemIndex,
                onNavItemClick = { currentNavItemIndex ->
                    when (currentNavItemIndex) {
                        0 -> {
                            navigateBetweenNavItems(
                                route = RouteHub.Home(),
                                navController = navController
                            )
                        }

                        1 -> {
                            if (isArrivingForFirstTimeToCollection
                                || hasArtworkBeenNavigatedFromCollection
                            ) {
                                navigationViewModel.onNavBarHiding()
                            }

                            navigateBetweenNavItems(
                                route = RouteHub.Collection,
                                navController = navController
                            )
                        }

                        2 -> {
                            if (isArrivingForFirstTimeToArtisticCulture) {
                                navigationViewModel.onNavBarHiding()
                            }

                            navigateBetweenNavItems(
                                route = RouteHub.ArtisticCulture,
                                navController = navController
                            )
                        }
                    }
                }
            )
        }
    }) { _ ->

        NavHost(
            navController = navController,
            startDestination = RouteHub.UserStateInitialChecking
        ) {
            composable<RouteHub.UserStateInitialChecking> { navBackStackEntry ->
                val splashScreenViewModel: SplashScreenViewModel =
                    hiltViewModel(viewModelStoreOwner = navBackStackEntry)

                LaunchedEffect(Unit) {
                    splashScreenViewModel.onUserInitialChecking()
                }

                val hasInternetConnection by
                splashScreenViewModel.hasInternetConnection.observeAsState(
                    initial = null
                )

                val hasActiveSession by
                splashScreenViewModel.hasActiveSession.observeAsState(
                    initial = null
                )

                if (hasInternetConnection == null) {
                    CommonLoadingScreen()
                } else if (hasInternetConnection == false) {
                    CommonNoInternetConnectionModal()
                }

                LaunchedEffect(hasActiveSession) {
                    if (hasInternetConnection == true) {
                        if (hasActiveSession == true) {
                            navController.navigate(route = RouteHub.Home()) {
                                popUpTo<RouteHub.UserStateInitialChecking> {
                                    inclusive = true
                                }
                            }
                        } else {
                            navController.navigate(route = RouteHub.InitialMenu) {
                                popUpTo<RouteHub.UserStateInitialChecking> {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }
            }

            composable<RouteHub.InitialMenu> { navBackStackEntry ->
                val userLoginViewModel: UserLoginViewModel =
                    hiltViewModel(viewModelStoreOwner = navBackStackEntry)

                val showLoginModal by
                userLoginViewModel.showLoginModal.observeAsState(initial = false)

                val isLoading by
                userLoginViewModel.isLoading.observeAsState(initial = false)

                val navigateToHome by
                userLoginViewModel.navigateToHome.observeAsState(initial = null)

                val showNoInternetConnectionModal by
                userLoginViewModel.showNoInternetConnectionModal.observeAsState(
                    initial = false
                )

                val showUserNotFountModal by
                userLoginViewModel.showUserNotFoundModal.observeAsState(initial = false)

                if (!isLoading && navigateToHome == null) {
                    InitialMenuScreen(onGoToRegisterButtonPress = {
                        navController.navigate(
                            route = RouteHub.Registration
                        )
                    }) {
                        userLoginViewModel.onLoginModalOpening()
                    }

                    if (showLoginModal) {
                        LoginModal(
                            userLoginViewModel = userLoginViewModel
                        )
                    }
                } else if (navigateToHome == false) {
                    if (showNoInternetConnectionModal) {
                        CommonNoInternetConnectionModal()
                    } else if (showUserNotFountModal) {
                        UserNotFoundModal {
                            userLoginViewModel.onUserNotFoundModalClosing()
                        }
                    }
                } else {
                    CommonLoadingScreen()
                }

                LaunchedEffect(navigateToHome) {
                    if (navigateToHome == true) {
                        navController.navigate(route = RouteHub.Home()) {
                            popUpTo<RouteHub.InitialMenu> { inclusive = true }
                        }
                    }
                }
            }

            composable<RouteHub.Registration> { navBackStackEntry ->
                val userRegistrationViewModel: UserRegistrationViewModel =
                    hiltViewModel(viewModelStoreOwner = navBackStackEntry)

                val isLoading by userRegistrationViewModel.isLoading.observeAsState(
                    initial = false
                )

                val navigateToHome by userRegistrationViewModel.navigateToHome.observeAsState(
                    initial = null
                )

                val showNoInternetConnectionModal by userRegistrationViewModel.showNoInternetConnectionModal.observeAsState(
                    initial = false
                )

                if (!isLoading && navigateToHome == null) {
                    UserRegistrationScreen(
                        userRegistrationViewModel = userRegistrationViewModel,
                        context = applicationContext,
                        onReturnButtonPress = { navController.popBackStack() },
                        onRegisterButtonPress = {
                            userRegistrationViewModel.onUserRegistrationScreenButtonPress()
                        })
                } else {
                    CommonLoadingScreen()

                    if (navigateToHome == false) {
                        if (showNoInternetConnectionModal) {
                            CommonNoInternetConnectionModal()
                        } else {
                            EmailAlreadyInUseModal {
                                navController.navigate(route = RouteHub.UserStateInitialChecking) {
                                    popUpTo<RouteHub.Registration> {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    }
                }
                LaunchedEffect(navigateToHome) {
                    if (navigateToHome == true) {
                        navController.navigate(route = RouteHub.Home()) {
                            popUpTo<RouteHub.InitialMenu> { inclusive = true }
                        }
                    }
                }
            }

            composable<RouteHub.Home> { navBackStackEntry ->
                val homeDestination: RouteHub.Home = navBackStackEntry.toRoute()

                val homeViewModel: HomeViewModel =
                    hiltViewModel(viewModelStoreOwner = navBackStackEntry)

                val isLoading by homeViewModel.isLoading.observeAsState(initial = true)

                val showNoInternetConnectionModal by
                homeViewModel.showNoInternetConnectionModal.observeAsState(initial = false)


                val showSupportContactModal by
                homeViewModel.showContactWithSupportModal.observeAsState(initial = false)

                val showUserProfileModal by
                homeViewModel.showUserProfileModal.observeAsState(initial = false)

                LaunchedEffect(Unit) {
                    if (isLoading) {
                        homeViewModel.onHomeScreenArrival(
                            artworkId = homeDestination.artworkId,
                            addArtworkToUserFavoriteArtworks = homeDestination
                                .addArtworkToUserFavoriteArtworks
                        )
                    }
                }

                if (isLoading) {
                    CommonLoadingScreen()
                } else if (showNoInternetConnectionModal) {
                    CommonNoInternetConnectionModal()
                } else {
                    val userProfile by homeViewModel.userProfile.observeAsState()

                    LaunchedEffect(Unit) {
                        if (hasArtworkBeenAddedToCollections) {
                            if (hasArtworkBeenMarkedAsFavorite != null) {
                                Toast.makeText(
                                    applicationContext,
                                    "¡Los cambios se han realizado con éxito!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "¡El cuadro se ha añadido a tus colecciones con éxito!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            navigationViewModel.onHomeArrival()
                        } else if (hasArtworkBeenDeletedFromCollections) {
                            if (hasArtworkBeenMarkedAsFavorite != null) {
                                Toast.makeText(
                                    applicationContext,
                                    "¡Los cambios se han realizado con éxito!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "¡El cuadro se ha eliminado de tus colecciones con éxito!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            navigationViewModel.onHomeArrival()
                        } else if (homeDestination.artworkId != null
                            && hasArtworkBeenMarkedAsFavorite != null
                        ) {
                            if (hasArtworkBeenMarkedAsFavorite!!) {
                                Toast.makeText(
                                    applicationContext,
                                    "¡El cuadro se ha añadido a tu lista de favoritos con éxito!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "¡El cuadro se ha eliminado de tu lista de favoritos con éxito!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            navigationViewModel.onHomeArrival()
                        }

                        navigationViewModel.onNavBarShowing()
                    }

                    HomeScreen(
                        homeViewModel = homeViewModel,
                        userProfile = userProfile!!,
                        onNavBarHiding = {
                            navigationViewModel.onNavBarHiding()
                        },
                        onNavBarShowing = {
                            navigationViewModel.onNavBarShowing()
                        },
                        onArtworkPreviewClick = { artworkId ->

                            navController.navigate(route = RouteHub.Artwork(artworkId = artworkId))

                            navigationViewModel.onNavBarHiding()
                        }) {
                        homeViewModel.logOutUser()

                        navigationViewModel.onUserLogOut()

                        navigationViewModel.onNavBarHiding()

                        navController.navigate(route = RouteHub.UserStateInitialChecking) {
                            popUpTo<RouteHub.Home> { inclusive = true }
                        }
                    }

                    if (showSupportContactModal) {
                        ContactWithSupportModal(homeViewModel = homeViewModel)
                    }

                    if (showUserProfileModal) {
                        UserProfileModal(userProfile = userProfile!!, homeViewModel = homeViewModel)
                    }
                }
            }

            composable<RouteHub.Artwork> { navBackStackEntry ->
                val artworkDestination: RouteHub.Artwork = navBackStackEntry.toRoute()

                val artworkViewModel: ArtworkViewModel =
                    hiltViewModel(viewModelStoreOwner = navBackStackEntry)

                val isLoading by artworkViewModel.isLoading.observeAsState(initial = true)

                val showNoInternetConnectionModal
                        by artworkViewModel.showNoInternetConnectionModal.observeAsState(
                            initial = false
                        )

                val showNotAnyCollectionsCreatedModalInDeletionOption
                        by artworkViewModel.showNotAnyCollectionsCreatedModalInDeletionOption
                            .observeAsState(initial = false)

                val showNotAnyCollectionsCreatedModalInAdditionOption
                        by artworkViewModel.showNotAnyCollectionsCreatedModalInAdditionOption
                            .observeAsState(initial = false)

                val showNotAnyArtworksInCollectionsModal by artworkViewModel
                    .showNotAnyArtworksInCollectionsModal.observeAsState(
                        initial = false
                    )

                val showArtworkInAllCollectionsModal by artworkViewModel
                    .showArtworkInAllCollectionsModal.observeAsState(
                        initial = false
                    )

                val showArtworkAdditionToCollectionsModal by artworkViewModel
                    .showArtworkAdditionToCollectionsModal.observeAsState(
                        initial = false
                    )

                val showArtworkDeletionToCollectionsModal by artworkViewModel
                    .showArtworkDeletionFromCollectionsModal.observeAsState(
                        initial = false
                    )

                val navigateToHome by
                artworkViewModel.navigateToHome.observeAsState()

                val artwork by artworkViewModel.artwork.observeAsState()

                LaunchedEffect(Unit) {
                    artworkViewModel.onArtworkInformationScreenArrival(
                        artworkId = artworkDestination.artworkId
                    )
                }

                LaunchedEffect(navigateToHome) {
                    if (navigateToHome == true) {
                        if (currentNavItemIndex != 0) {
                            navigationViewModel.onNavItemClick(currentNavItemIndex = 0)
                        }

                        if (hasArtworkBeenMarkedAsFavorite != null) {
                            navController.navigate(
                                route =
                                    RouteHub.Home(
                                        artworkId = artwork!!.id,
                                        addArtworkToUserFavoriteArtworks =
                                            hasArtworkBeenMarkedAsFavorite!!
                                    )
                            ) {
                                popUpTo<RouteHub.Home> { inclusive = true }
                            }
                        } else {
                            navController.navigate(route = RouteHub.Home()) {
                                popUpTo<RouteHub.Home> { inclusive = true }
                            }
                        }
                    }
                }

                if (!isLoading && navigateToHome == null) {
                    val collectionsWithThatArtwork by
                    artworkViewModel.collectionsWithThatArtwork.observeAsState()

                    val collectionsWithoutThatArtwork by
                    artworkViewModel.collectionsWithoutThatArtwork.observeAsState()

                    val hasArtworkBeenMarkedAsFavorite by
                    artworkViewModel.hasArtworkBeenMarkedAsFavorite.observeAsState(
                        initial = artwork!!.hasBeenMarkedAsFavorite
                    )

                    ArtworkScreen(
                        artworkViewModel = artworkViewModel,
                        artwork = artwork!!,
                        hasArtworkBeenMarkedAsFavorite = hasArtworkBeenMarkedAsFavorite,
                        collectionsWithThatArtwork = collectionsWithThatArtwork!!,
                        collectionsWithoutThatArtwork = collectionsWithoutThatArtwork!!,
                        onArtworkAdditionToRemainingCollection = {
                            navigationViewModel.onArtworkAdditionToCollections()

                            if (hasArtworkBeenMarkedAsFavorite != artwork!!.hasBeenMarkedAsFavorite) {
                                navigationViewModel.onArtworkMarkedAsFavoriteStateChange(
                                    hasArtworkBeenMarkedAsFavorite = hasArtworkBeenMarkedAsFavorite
                                )
                            }

                            artworkViewModel.onArtworkAdditionToCollections(
                                artworkId = artworkDestination.artworkId
                            )
                        },
                        onArtworkDeletionFromRemainingCollection = {
                            navigationViewModel.onArtworkDeletionFromCollections()

                            if (hasArtworkBeenMarkedAsFavorite != artwork!!.hasBeenMarkedAsFavorite) {
                                navigationViewModel.onArtworkMarkedAsFavoriteStateChange(
                                    hasArtworkBeenMarkedAsFavorite = hasArtworkBeenMarkedAsFavorite
                                )
                            }

                            artworkViewModel.onArtworkDeletionFromCollections(
                                artworkId = artworkDestination.artworkId
                            )
                        }
                    ) {
                        if (hasArtworkBeenMarkedAsFavorite != artwork!!.hasBeenMarkedAsFavorite) {
                            if (currentNavItemIndex != 0) {
                                navigationViewModel.onNavItemClick(currentNavItemIndex = 0)
                            }

                            navigationViewModel.onArtworkMarkedAsFavoriteStateChange(
                                hasArtworkBeenMarkedAsFavorite = hasArtworkBeenMarkedAsFavorite
                            )

                            navController.navigate(
                                route =
                                    RouteHub.Home(
                                        artworkId = artwork!!.id,
                                        addArtworkToUserFavoriteArtworks = hasArtworkBeenMarkedAsFavorite
                                    )
                            ) {
                                popUpTo<RouteHub.Home> { inclusive = true }
                            }
                        } else {
                            navController.popBackStack()
                        }
                    }

                    BackHandler { //Este composable se ejecuta cuando el usuario pulsa al botón o
                        // realiza el gesto de retroceder en el dispositivo.
                        if (hasArtworkBeenMarkedAsFavorite != artwork!!.hasBeenMarkedAsFavorite) {
                            if (currentNavItemIndex != 0) {
                                navigationViewModel.onNavItemClick(currentNavItemIndex = 0)
                            }

                            navigationViewModel.onArtworkMarkedAsFavoriteStateChange(
                                hasArtworkBeenMarkedAsFavorite = hasArtworkBeenMarkedAsFavorite
                            )

                            navController.navigate(
                                route =
                                    RouteHub.Home(
                                        artworkId = artwork!!.id,
                                        addArtworkToUserFavoriteArtworks = hasArtworkBeenMarkedAsFavorite
                                    )
                            ) {
                                popUpTo<RouteHub.Home> { inclusive = true }
                            }
                        } else {
                            navController.popBackStack()
                        }
                    }

                    if (showArtworkInAllCollectionsModal) {
                        ArtworkInAllCollectionsModal(artworkViewModel = artworkViewModel)
                    } else if (showNotAnyCollectionsCreatedModalInAdditionOption) {
                        NotAnyCollectionsCreatedModalInArtworkAdditionOption(
                            artworkViewModel = artworkViewModel
                        )
                    } else if (showNotAnyCollectionsCreatedModalInDeletionOption) {
                        NotAnyCollectionsCreatedModalInArtworkDeletionOption(
                            artworkViewModel = artworkViewModel
                        )
                    } else if (showNotAnyArtworksInCollectionsModal) {
                        NotAnyArtworksInCollectionsModal(artworkViewModel = artworkViewModel)
                    } else if (showArtworkAdditionToCollectionsModal) {
                        ArtworkAdditionToCollectionsModal(
                            artworkViewModel = artworkViewModel,
                            collectionsWithoutThatArtwork = collectionsWithoutThatArtwork!!
                        ) {
                            navigationViewModel.onArtworkAdditionToCollections()

                            if (hasArtworkBeenMarkedAsFavorite != artwork!!.hasBeenMarkedAsFavorite) {
                                navigationViewModel.onArtworkMarkedAsFavoriteStateChange(
                                    hasArtworkBeenMarkedAsFavorite = hasArtworkBeenMarkedAsFavorite
                                )
                            }

                            artworkViewModel.onArtworkAdditionToCollections(
                                artworkId = artworkDestination.artworkId
                            )
                        }
                    } else if (showArtworkDeletionToCollectionsModal) {
                        ArtworkDeletionFromCollectionsModal(
                            artworkViewModel = artworkViewModel,
                            collectionsWithThatArtwork = collectionsWithThatArtwork!!
                        ) {
                            navigationViewModel.onArtworkDeletionFromCollections()

                            if (hasArtworkBeenMarkedAsFavorite != artwork!!.hasBeenMarkedAsFavorite) {
                                navigationViewModel.onArtworkMarkedAsFavoriteStateChange(
                                    hasArtworkBeenMarkedAsFavorite = hasArtworkBeenMarkedAsFavorite
                                )
                            }

                            artworkViewModel.onArtworkDeletionFromCollections(
                                artworkId = artworkDestination.artworkId
                            )
                        }
                    }
                } else if (showNoInternetConnectionModal) {
                    CommonNoInternetConnectionModal()
                } else {
                    CommonLoadingScreen()
                }
            }
            navigation<RouteHub.Collection>(startDestination = RouteHub.Collection.Previews) {
                composable<RouteHub.Collection.Previews> { navBackStackEntry ->
                    val collectionViewModel: CollectionViewModel =
                        hiltViewModel(viewModelStoreOwner = navBackStackEntry)

                    val isLoading by collectionViewModel
                        .isLoading.observeAsState(initial = false)

                    val showNoInternetConnectionModal by collectionViewModel
                        .showNoInternetConnectionModal.observeAsState(initial = false)

                    val showCollectionCreationModal by collectionViewModel
                        .showCollectionCreationModal.observeAsState(initial = false)

                    val showNotAnyCollectionsToDeleteModal by
                    collectionViewModel.showNotAnyCollectionsToDeleteModal
                        .observeAsState(initial = false)

                    val showCollectionBatchDeletionModal by collectionViewModel
                        .showCollectionBatchDeletionModal.observeAsState(initial = false)

                    val showNotAnyCollectionsToRenameModal by
                    collectionViewModel
                        .showNotAnyCollectionsToRenameModal.observeAsState(initial = false)

                    val showCollectionRenamingOptionModal by
                    collectionViewModel
                        .showCollectionRenamingOptionModal.observeAsState(initial = false)

                    val showCollectionRenamingModal by
                    collectionViewModel.showCollectionRenamingModal.observeAsState(initial = false)

                    val showUniqueCollectionToDeleteModal by
                    collectionViewModel.showUniqueCollectionToDeleteModal.observeAsState(initial = false)


                    LaunchedEffect(Unit) {
                        if (isArrivingForFirstTimeToCollection) {
                            navigationViewModel.onCollectionFirstTimeArrival()

                            collectionViewModel.onCollectionPreviewScreenArrival()
                        } else if (hasArtworkBeenNavigatedFromCollection) {
                            navigationViewModel.onCollectionArrival()

                            collectionViewModel.onCollectionPreviewScreenArrival()
                        }
                    }

                    BackHandler {
                        navigationViewModel.onNavItemClick(currentNavItemIndex = 0)

                        navigateBetweenNavItems(
                            route = RouteHub.Home(),
                            navController = navController
                        )
                    }

                    if (isArrivingForFirstTimeToCollection || isLoading
                        || hasArtworkBeenNavigatedFromCollection
                    ) {
                        CommonLoadingScreen()
                    } else if (showNoInternetConnectionModal) {
                        CommonNoInternetConnectionModal()
                    } else {
                        val userCollections by collectionViewModel.userCollections.observeAsState()

                        LaunchedEffect(Unit) {
                            navigationViewModel.onNavBarShowing()
                        }

                        CollectionPreviewScreen(
                            collectionViewModel = collectionViewModel,
                            userCollections = userCollections!!
                        ) { collectionId, collectionTitle ->
                            navController.navigate(
                                route = RouteHub.Collection.Artworks(
                                    collectionId = collectionId,
                                    collectionTitle = collectionTitle
                                )
                            )

                            navigationViewModel.onNavBarHiding()
                        }

                        when {
                            showCollectionCreationModal -> {
                                CollectionCreationModal(collectionViewModel = collectionViewModel) {
                                    navigationViewModel.onNavBarHiding()

                                    collectionViewModel.onCollectionCreationModalConfirmButtonClick()
                                }
                            }

                            showNotAnyCollectionsToRenameModal -> {
                                NotAnyCollectionsToRenameModal(collectionViewModel = collectionViewModel)
                            }

                            showCollectionRenamingOptionModal -> {
                                CollectionRenamingOptionModal(
                                    collectionViewModel = collectionViewModel,
                                    userCollections = userCollections!!
                                )
                            }

                            showCollectionRenamingModal -> {
                                CollectionRenamingModal(collectionViewModel = collectionViewModel) {
                                    navigationViewModel.onNavBarHiding()

                                    collectionViewModel.onCollectionRenamingModalConfirmButtonClick()
                                }
                            }

                            showNotAnyCollectionsToDeleteModal -> {
                                NotAnyCollectionsToDeleteModal(collectionViewModel = collectionViewModel)
                            }

                            showUniqueCollectionToDeleteModal -> {
                                UniqueCollectionToDeleteModal(
                                    collectionViewModel = collectionViewModel,
                                    uniqueCollectionToDeleteTitle = userCollections!!.get(index = 0).title
                                ) {
                                    navigationViewModel.onNavBarHiding()

                                    collectionViewModel.onCollectionBatchDeletion()
                                }
                            }

                            showCollectionBatchDeletionModal -> {
                                CollectionBatchDeletionModal(
                                    collectionViewModel = collectionViewModel,
                                    userCollections = userCollections!!
                                ) {
                                    navigationViewModel.onNavBarHiding()

                                    collectionViewModel.onCollectionBatchDeletion()
                                }
                            }
                        }
                    }
                }

                composable<RouteHub.Collection.Artworks> { navBackStackEntry ->
                    val collectionArtworkDestination: RouteHub.Collection.Artworks =
                        navBackStackEntry.toRoute()

                    val collectionViewModel: CollectionViewModel =
                        hiltViewModel(viewModelStoreOwner = navBackStackEntry)

                    val isLoading by collectionViewModel
                        .isLoading.observeAsState(initial = true)

                    val showNoInternetConnectionModal by collectionViewModel
                        .showNoInternetConnectionModal.observeAsState(initial = false)

                    LaunchedEffect(Unit) {
                        collectionViewModel.onCollectionArtworkScreenArrival(
                            collectionId = collectionArtworkDestination.collectionId
                        )
                    }

                    BackHandler {
                        navigationViewModel.onReturnToCollectionPreviews()

                        navController.popBackStack()
                    }

                    if (isLoading) {
                        CommonLoadingScreen()
                    } else if (showNoInternetConnectionModal) {
                        CommonNoInternetConnectionModal()
                    } else {
                        val collectionArtworks by collectionViewModel
                            .collectionArtworks.observeAsState()

                        CollectionArtworkScreen(
                            collectionTitle = collectionArtworkDestination.collectionTitle,
                            collectionArtworks = collectionArtworks!!,
                            onCollectionPreviewScreenReturn = {
                                navigationViewModel.onReturnToCollectionPreviews()

                                navController.popBackStack()
                            }
                        ) { artworkId ->
                            navController.navigate(route = RouteHub.Artwork(artworkId = artworkId))

                            navigationViewModel.onArtworkArrivalFromCollection()
                        }
                    }
                }
            }
        }
    }
}