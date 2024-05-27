/*
package com.po.bookDB.ui.books

import android.annotation.SuppressLint
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.po.bookDB.ui.auth.LoginScreen
import com.po.bookDB.ui.auth.LoginViewModel
import com.po.bookDB.ui.auth.SignUpScreen
import com.po.bookDB.ui.graph.LoginRoutes
import com.po.bookDB.ui.graph.NestedRoute
import com.po.bookDB.ui.viewModel.BookViewModel

@SuppressLint("UnrememberedGetBackStackEntry")
fun NavGraphBuilder.bookNavGraph(
    navController: NavController,
    loginViewModel: LoginViewModel,
) {
    navigation(
        startDestination = AppDestination.Login.route,
        route = Routes.LOGIN
    ) {

        composable(route = AppDestination.Book.route) {
            val parentEntry = remember {
                navController.getBackStackEntry(Routes.BOOK_ROUTE)
            }
            val bookViewModel = hiltViewModel<BookViewModel>(parentEntry)
            BookScreen(navController = navController, vm = bookViewModel)
        }

        composable(route = LoginRoutes.SignIn.name) {
            LoginScreen(
                onNavToHomePage = {
                    navController.navigate(NestedRoute.Main.name) {
                        launchSingleTop = true
                        popUpTo(route = LoginRoutes.SignIn.name) {
                            inclusive = true
                        }
                    }
                },
                loginViewModel = loginViewModel
            ) {
                navController.navigate(LoginRoutes.Signup.name) {
                    launchSingleTop = true
                    popUpTo(LoginRoutes.SignIn.name) {
                        inclusive = false
                    }
                }
            }
        }
        composable(route = LoginRoutes.Signup.name) {
            SignUpScreen(
                onNavToHomePage = {
                    navController.navigate(NestedRoute.Main.name) {
                        popUpTo(LoginRoutes.Signup.name) {
                            inclusive = true
                        }
                    }
                },
                loginViewModel = loginViewModel
            ) {
                navController.navigate(LoginRoutes.SignIn.name)
            }
        }

    }
}*/
