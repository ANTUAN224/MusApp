package com.project.musapp

import android.os.Bundle
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
import com.project.musapp.core.feature.navigation.item.presentation.ui.MusAppNavigationBar
import com.project.musapp.core.feature.navigation.item.presentation.viewmodel.NavigationViewModel
import com.project.musapp.core.feature.navigation.routing.RouteHub
import com.project.musapp.feature.user.register.presentation.navigation.registrationNavGraph
import com.project.musapp.feature.user.initialchecking.presentation.ui.UserInitialCheckingScreen
import com.project.musapp.feature.user.initialchecking.presentation.viewModel.UserInitialCheckingViewModel
import com.project.musapp.feature.user.initialmenu.presentation.ui.InitialMenuScreen
import com.project.musapp.feature.user.login.presentation.ui.UserLoginModal
import com.project.musapp.feature.user.login.presentation.viewmodel.UserLoginViewModel
import com.project.musapp.ui.theme.MusAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint //Permite que se puedan inyectar dependencias dentro de la clase.
class MainActivity : ComponentActivity() {
    private val navigationViewModel: NavigationViewModel by viewModels() //Simplifica la obtención de una instancia de un ViewModel con lazy initialization.

    private val userInitialCheckingViewModel: UserInitialCheckingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this) // Inicialización de Firebase al ejecutar la app
        enableEdgeToEdge()
        setContent {
            MusAppTheme(darkTheme = false) {
                val hasInternetConnection by
                userInitialCheckingViewModel.hasInternetConnection.observeAsState(initial = null)

                val hasActiveSession by
                userInitialCheckingViewModel.hasActiveSession.observeAsState(initial = null)

                val currentNavItemIndex by
                navigationViewModel.navItemIndex.observeAsState(initial = 0)

                val showNavBar by
                navigationViewModel.showNavBar.observeAsState(initial = false)

                val navController = rememberNavController()

                LaunchedEffect(Unit) {
                    userInitialCheckingViewModel.onUserInitialChecking()
                }

                Scaffold(bottomBar = {
                    if (showNavBar) MusAppNavigationBar(
                        navigationViewModel = navigationViewModel,
                        navItemIndex = currentNavItemIndex
                    )
                }) { innerPadding ->

                    LaunchedEffect(hasInternetConnection, hasActiveSession) {
                        if (hasInternetConnection == null || hasActiveSession == null)
                            return@LaunchedEffect

                        if (hasInternetConnection == true) {
                            if (hasActiveSession == true) {
                                navigationViewModel.onHomeScreenNavigation()

                                navController.navigate(route = RouteHub.Home) {
                                    popUpTo<RouteHub.UserStateInitialChecking> { inclusive = true }
                                }
                            } else {
                                navController.navigate(route = RouteHub.InitialMenu) {
                                    popUpTo<RouteHub.UserStateInitialChecking> { inclusive = true }
                                }
                            }
                        }
                    }

                    NavHost(
                        navController = navController,
                        startDestination = RouteHub.UserStateInitialChecking
                    ) {
                        composable<RouteHub.UserStateInitialChecking> {
                            UserInitialCheckingScreen(
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

                        registrationNavGraph(
                            navController,
                            context = applicationContext,
                            navigationViewModel = navigationViewModel,
                            title = "Registro de usuario"
                        )
                    }
                }
            }
        }
    }
}