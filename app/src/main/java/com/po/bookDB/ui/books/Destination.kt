package com.po.bookDB.ui.books

object Routes {
    const val ROOT_ROUTE = "root"
    const val BOOK_ROUTE = "book"
    const val SIGN_UP_ROUTE = "signup"
    const val LOGIN = "login"
}

object ArgsConstants {
    const val MOVIE_VO = "bookVO"
}

sealed class AppDestination(val route: String) {
    object Splash : AppDestination(route = "splash_screen")
    object Book : AppDestination(route = "book_screen")

    object SignUp : AppDestination(route = "sign_up_screen")
    object Login: AppDestination( route =  "login_screen")
}