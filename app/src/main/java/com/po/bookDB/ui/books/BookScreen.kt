package com.po.bookDB.ui.books

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.po.bookDB.ui.viewModel.BookEvent
import com.po.bookDB.ui.viewModel.BookFavAction
import com.po.bookDB.ui.viewModel.BookViewModel
import com.po.bookDB.R
import com.po.bookDB.ui.common.CustomHorizontalSpacer
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BookScreen(
    navToLoginPage: () -> Unit,
    navToSearchPage: () -> Unit,
    navToFavoritePage: () -> Unit,
) {
    val vm = hiltViewModel<BookViewModel>()
    BookView(
        vm = vm,
        navToLoginPage = navToLoginPage,
        navToSearchPage = navToSearchPage,
        navToFavoritePage = navToFavoritePage
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookView(
    vm: BookViewModel,
    navToLoginPage: () -> Unit,
    navToSearchPage: () -> Unit,
    navToFavoritePage: () -> Unit
) {
    val snackState = remember { SnackbarHostState() }
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        LogoutDialog(onDismiss = { showDialog = false }, onConfirm = { navToLoginPage() })
    }
    LaunchedEffect(key1 = true) {
        vm.getBookApi()
        vm.bookEvent.collectLatest {
            when (it) {
                is BookEvent.SaveFavBook -> {

                }

                is BookEvent.ShowSnackBar -> {
                    snackState.showSnackbar(it.message, duration = SnackbarDuration.Short)
                }
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Book Store",
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navToSearchPage()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "Menu Icon"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navToFavoritePage()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_item_fav),
                            contentDescription = "Fav Icon"
                        )
                    }
                    CustomHorizontalSpacer(size = 8.dp)
                    IconButton(onClick = { showDialog = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_logout),
                            contentDescription = "Search Icon"
                        )
                    }
                },

                )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackState)
        }
    ) {
        Modifier.padding(it)
        BookContent(
            onItemClicked = { bookObj ->
                vm.onBookAction(
                    BookFavAction.ClickFav(
                        bookObj = bookObj
                    )
                )
            },
            resourceBook = vm.bookApiData.value.books,
            onRetry = {},
        )
    }
}

@Composable
fun LogoutDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Logout")
        },
        text = {
            Text("Are you sure you want to logout?")
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                    onDismiss()
                }) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss
            ) {
                Text("Cancel")
            }
        }
    )
}