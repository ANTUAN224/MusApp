package com.project.musapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.project.musapp.core.navigation.item.presentation.ui.MusAppNavigationBar
import com.project.musapp.core.navigation.item.presentation.viewmodel.NavigationViewModel
import com.project.musapp.core.navigation.routing.RouteHub
import com.project.musapp.feature.user.auth.login.presentation.ui.UserLoginModal
import com.project.musapp.feature.user.auth.login.presentation.viewmodel.UserLoginViewModel
import com.project.musapp.feature.user.auth.registration.presentation.ui.UserRegistrationScreen
import com.project.musapp.feature.user.auth.registration.presentation.viewmodel.UserRegistrationViewModel
import com.project.musapp.initialchecking.presentation.ui.UserStateInitialCheckingScreen
import com.project.musapp.initialchecking.presentation.viewModel.UserStateInitialCheckingViewModel
import com.project.musapp.feature.initialmenu.presentation.ui.InitialMenuScreen
import com.project.musapp.ui.theme.MusAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint //Permite que se puedan inyectar dependencias dentro de la clase.
class MainActivity : ComponentActivity() {
    private val navigationViewModel: NavigationViewModel by viewModels() //Simplifica la obtención de una instancia de un ViewModel con lazy initialization.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this) // Inicialización de Firebase al ejecutar la app
        enableEdgeToEdge()
        setContent {
            MusAppTheme(darkTheme = false) {
                val currentNavItemIndex by
                navigationViewModel.navItemIndex.observeAsState(initial = 0)

                val showNavBar by
                navigationViewModel.showNavBar.observeAsState(initial = false)

                val navController = rememberNavController()

                Scaffold(bottomBar = {
                    if (showNavBar) MusAppNavigationBar(
                        navigationViewModel = navigationViewModel,
                        navItemIndex = currentNavItemIndex
                    )
                }) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = RouteHub.UserStateInitialChecking
                    ) {
                        composable<RouteHub.UserStateInitialChecking> { navBackStackEntry ->
                            val userStateInitialCheckingViewModel: UserStateInitialCheckingViewModel =
                                hiltViewModel(viewModelStoreOwner = navBackStackEntry)

                            LaunchedEffect(Unit) {
                                userStateInitialCheckingViewModel.onUserInitialChecking()
                            }

                            val hasInternetConnection by
                            userStateInitialCheckingViewModel.hasInternetConnection.observeAsState(
                                initial = null
                            )

                            val hasActiveSession by
                            userStateInitialCheckingViewModel.hasActiveSession.observeAsState(
                                initial = null
                            )

                            LaunchedEffect(hasInternetConnection, hasActiveSession) {
                                if (hasInternetConnection == null || hasActiveSession == null)
                                    return@LaunchedEffect

                                if (hasInternetConnection == true) {
                                    if (hasActiveSession == true) {
                                        navigationViewModel.onHomeScreenNavigation()

                                        navController.navigate(route = RouteHub.Home) {
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

                            UserStateInitialCheckingScreen(
                                hasInternetConnection = hasInternetConnection
                            )
                        }

                        composable<RouteHub.InitialMenu> { navBackStackEntry ->
                            val userLoginViewModel: UserLoginViewModel =
                                hiltViewModel(viewModelStoreOwner = navBackStackEntry)

                            val showLoginModal by
                            userLoginViewModel.showLoginModal.observeAsState(initial = false)

                            InitialMenuScreen(onGoToRegisterButtonPress = {
                                navController.navigate(
                                    route = RouteHub.Registration
                                )
                            }) {
                                userLoginViewModel.onLoginModalOpening()
                            }

                            if (showLoginModal) {
                                UserLoginModal(
                                    viewModel = userLoginViewModel,
                                    context = applicationContext
                                ) {
                                    navigationViewModel.onHomeScreenNavigation()

                                    navController.navigate(route = RouteHub.Home) {
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

                            UserRegistrationScreen(
                                viewModel = userRegistrationViewModel,
                                context = applicationContext,
                                isLoading = isLoading,
                                navigateToHome = navigateToHome,
                                showNoInternetConnectionModal = showNoInternetConnectionModal,
                                onReturnButtonPress = { navController.popBackStack() },
                                onRegisterButtonPress = {
                                    userRegistrationViewModel.onUserRegistrationScreenButtonPress()
                                }) {
                                navController.navigate(route = RouteHub.UserStateInitialChecking) {
                                    popUpTo<RouteHub.Registration> { inclusive = true }
                                }
                            }

                            LaunchedEffect(navigateToHome) {
                                if (navigateToHome == true) {
                                    navigationViewModel.onHomeScreenNavigation()

                                    navController.navigate(route = RouteHub.Home) {
                                        popUpTo<RouteHub.Registration> { inclusive = true }
                                    }
                                }
                            }
                        }

                        composable<RouteHub.Home> {
                            LaunchedEffect(Unit) {
                                Toast.makeText(
                                    applicationContext,
                                    "Bienvenido a casita",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        }
    }
}