package com.project.musapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Scaffold
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
                val hasInternetConnection =
                    userInitialCheckingViewModel.hasInternetConnection.observeAsState(initial = null).value

                val hasActiveSession =
                    userInitialCheckingViewModel.hasActiveSession.observeAsState(initial = null).value

                val currentNavItemIndex =
                    navigationViewModel.navItemIndex.observeAsState(initial = 0).value

                val showNavBar =
                    navigationViewModel.showNavBar.observeAsState(initial = false).value

                val navController = rememberNavController()

                userInitialCheckingViewModel.executeUserInitialChecking()

                Scaffold(bottomBar = {
                    if (showNavBar) MusAppNavigationBar(
                        navigationViewModel = navigationViewModel,
                        navItemIndex = currentNavItemIndex
                    )
                }) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = if (hasInternetConnection == true && hasActiveSession == false) {
                            RouteHub.InitialMenu
                        } else if (hasActiveSession == true) {
                            navigationViewModel.onHomeScreenNavigation()
                            RouteHub.Home
                        } else {
                            RouteHub.UserInitialChecking
                        }
                    ) {
                        composable<RouteHub.UserInitialChecking> {
                            UserInitialCheckingScreen(
                                hasInternetConnection = hasInternetConnection
                            )
                        }

                        composable<RouteHub.InitialMenu> { navBackStackEntry ->
                            val userLoginViewModel: UserLoginViewModel =
                                hiltViewModel(viewModelStoreOwner = navBackStackEntry)

                            val showLoginModal =
                                userLoginViewModel.showLoginModal.observeAsState(initial = false).value

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
                                    navController.navigate(route = RouteHub.Home) {
                                        popUpTo<RouteHub.InitialMenu> { inclusive = true }
                                    }
                                }
                            }
                        }

                        registrationNavGraph(navController, context = applicationContext)
                    }
                }
            }
        }
    }
}