package com.project.musapp.navigation.presentation.entrypoint

import android.content.Context
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.project.musapp.feature.auth.presentation.login.ui.LoginModal
import com.project.musapp.feature.auth.presentation.login.ui.UserNotFoundModal
import com.project.musapp.feature.auth.presentation.login.viewmodel.UserLoginViewModel
import com.project.musapp.feature.auth.presentation.registration.ui.EmailAlreadyInUseModal
import com.project.musapp.feature.auth.presentation.registration.ui.UserRegistrationScreen
import com.project.musapp.feature.auth.presentation.registration.viewmodel.UserRegistrationViewModel
import com.project.musapp.feature.home.presentation.ui.HomeScreen
import com.project.musapp.feature.home.presentation.viewmodel.HomeViewModel
import com.project.musapp.navigation.presentation.initialmenu.ui.InitialMenuScreen
import com.project.musapp.navigation.presentation.navigationbar.ui.MusAppNavigationBar
import com.project.musapp.navigation.presentation.navigationbar.viewmodel.NavigationViewModel
import com.project.musapp.navigation.presentation.splashscreen.viewModel.SplashScreenViewModel
import com.project.musapp.navigation.routing.RouteHub
import com.project.musapp.ui.commoncomponents.CommonLoadingScreen
import com.project.musapp.ui.commoncomponents.CommonNoInternetConnectionModal

@Composable
fun NavigationEntryPoint(applicationContext: Context) {
    val navigationViewModel: NavigationViewModel = hiltViewModel()

    val currentNavItemIndex by
    navigationViewModel.navItemIndex.observeAsState(initial = 0)

    val showNavBar by
    navigationViewModel.showNavBar.observeAsState(initial = false)

    val navController = rememberNavController()

    Scaffold(bottomBar = {
        if (showNavBar) MusAppNavigationBar(
            navigationViewModel = navigationViewModel,
            currentNavItemIndex = currentNavItemIndex
        )
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

                LaunchedEffect(hasInternetConnection) {
                    if (hasInternetConnection == true) {
                        if (hasActiveSession == true) {
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
                        navController.navigate(route = RouteHub.Home) {
                            popUpTo<RouteHub.Registration> { inclusive = true }
                        }
                    }
                }
            }

            composable<RouteHub.Home> { navBackStackEntry ->
                val homeViewModel: HomeViewModel =
                    hiltViewModel(viewModelStoreOwner = navBackStackEntry)

                val isLoading by homeViewModel.isLoading.observeAsState(initial = true)

                val showNoInternetConnectionModal by homeViewModel.showNoInternetConnectionModal.observeAsState(
                    initial = false
                )

                LaunchedEffect(Unit) {
                    homeViewModel.getHomeData()
                }

                if (isLoading) {
                    CommonLoadingScreen()
                } else if (showNoInternetConnectionModal) {
                    CommonNoInternetConnectionModal()
                } else {
                    LaunchedEffect(Unit) {
                        navigationViewModel.onNavBarShowing()
                    }

                    HomeScreen(homeViewModel = homeViewModel) {
                        homeViewModel.logOutUser()

                        navigationViewModel.onNavBarHiding()

                        navController.navigate(route = RouteHub.UserStateInitialChecking) {
                            popUpTo<RouteHub.Home> {
                                inclusive = true
                            }
                        }
                    }
                }
            }
        }
    }
}