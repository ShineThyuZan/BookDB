package com.po.bookDB.ui.graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.po.bookDB.ui.auth.LoginScreen
import com.po.bookDB.ui.auth.AuthViewModel
import com.po.bookDB.ui.auth.SignUpScreen
import com.po.bookDB.ui.books.BookScreen
import com.po.bookDB.ui.books.FavoriteScreen
import com.po.bookDB.ui.books.SearchScreen
import com.po.bookDB.ui.books.SplashScreen

enum class LoginRoutes {
    Home,
    Signup,
    SignIn
}

enum class HomeRoute {
    Splash,
    Home,
    Search,
    Favorite
}

enum class NestedRoute {
    Splash,
    Main,
    Login,

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootNavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = NestedRoute.Splash.name,
    ) {
        composable(route = NestedRoute.Splash.name) {
            SplashScreen(navController = navController)
            //SplashLottieScreen(navController = navController)
        }
        authGraph(navController = navController)
        homeGraph(navController = navController)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = NestedRoute.Main.name
    ) {
        authGraph(navController = navController)
        homeGraph(
            navController = navController
        )
    }
}


fun NavGraphBuilder.authGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = LoginRoutes.SignIn.name,
        route = NestedRoute.Login.name
    ) {
        composable(route = LoginRoutes.SignIn.name) {
            val vm: AuthViewModel = hiltViewModel()
            LoginScreen(
                onNavToBookPage = {
                    navController.navigate(NestedRoute.Main.name) {
                        launchSingleTop = true
                        popUpTo(route = LoginRoutes.SignIn.name) {
                            inclusive = true
                        }
                    }
                },
                loginViewModel = vm,
                onNavToSignUpPage = {
                    navController.navigate(LoginRoutes.Signup.name) {
                        launchSingleTop = true
                        popUpTo(LoginRoutes.SignIn.name) {
                            inclusive = false
                        }
                    }
                },
                navController = navController
            )
        }
        composable(route = LoginRoutes.Signup.name) {
            val vm: AuthViewModel = hiltViewModel()
            SignUpScreen(
                onNavToLoginPage = {
                    navController.navigate(NestedRoute.Login.name) {
                        popUpTo(LoginRoutes.Signup.name) {
                            inclusive = true
                        }
                    }
                },
                loginViewModel = vm,
                navController = navController
            )
        }
        composable(route = LoginRoutes.Home.name) {
            val vm: AuthViewModel = hiltViewModel()
            SignUpScreen(
                onNavToLoginPage = {
                    navController.navigate(NestedRoute.Login.name) {
                        popUpTo(LoginRoutes.Home.name) {
                            inclusive = true
                        }
                    }
                },
                loginViewModel = vm,
                navController = navController
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.homeGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = HomeRoute.Home.name,
        route = NestedRoute.Main.name
    ) {
        composable(HomeRoute.Home.name) {
            BookScreen(
                navToLoginPage = {
                        navController.navigate(NestedRoute.Login.name) {
                            launchSingleTop = true
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                },
                navToSearchPage = {
                    navController.navigate(HomeRoute.Search.name) {
                        launchSingleTop = true
                        popUpTo(0) {
                            inclusive = true
                        }
                    }
                },
                navToFavoritePage = {
                    navController.navigate(HomeRoute.Favorite.name) {
                        launchSingleTop = true
                        popUpTo(0) {
                            inclusive = true
                        }
                    }
                }
            )
        }
       composable(HomeRoute.Search.name) {
            SearchScreen(
                navToBookPage = {
                        navController.navigate(NestedRoute.Main.name) {
                            launchSingleTop = true
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                })
        }
        composable(HomeRoute.Favorite.name) {
            FavoriteScreen(
                navToBookPage = {
                    navController.navigate(NestedRoute.Main.name) {
                        launchSingleTop = true
                        popUpTo(0) {
                            inclusive = true
                        }
                    }
                })
        }
    }
}