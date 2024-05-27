/*
package com.po.bookDB.ui.books

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.po.bookDB.ui.auth.LoginViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    loginViewModel: LoginViewModel
) {
    NavHost(
        navController = navController,
        startDestination = AppDestination.Splash.route,
        route = Routes.ROOT_ROUTE
    ) {
        composable(route = AppDestination.Splash.route) {
            SplashScreen(navController = navController)
        }
        bookNavGraph(navController = navController,
            loginViewModel = loginViewModel
        )
    }
}
*/
