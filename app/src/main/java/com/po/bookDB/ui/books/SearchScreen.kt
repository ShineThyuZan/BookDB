package com.po.bookDB.ui.books

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.po.bookDB.R
import com.po.bookDB.ui.viewModel.BookEvent
import com.po.bookDB.ui.viewModel.BookViewModel


@Composable
fun SearchScreen(
    navToBookPage: () -> Unit,
) {
    val vm = hiltViewModel<BookViewModel>()
    BookSearchView(
        vm = vm,
        navToBookPage = navToBookPage,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookSearchView(
    vm: BookViewModel,
    navToBookPage: () -> Unit,
) {
    LaunchedEffect(key1 = true) {
        //vm.updateSearchQuery(vm.bookUiState.value.query)
        vm.bookEvent.collect {
            when (it) {
               is  BookEvent.SaveFavBook -> {
                    // todo
                }
                is BookEvent.ShowSnackBar -> {}
            }
        }
    }
    Scaffold(
        topBar = {
           SearchTopBar(
               searchQuery = vm.bookUiState.value.query,
               onNavigation = {
                   IconButton(onClick = {
                     navToBookPage()
                   }) {
                       androidx.compose.material3.Icon(
                           painter = painterResource(id = R.drawable.ic_cross),
                           contentDescription = null,
                       )
                   }
               },
               onTextChanged = {
                   vm.updateSearchQuery(it)
               },
               onClearSearchQuery = {
                 vm.updateSearchQuery("")
               },
               onSearchClicked = { query ->
              //   vm.updateSearchQuery(query)
                   vm.search()
               }
           )
        },
    ) {
        Modifier.padding(it)
        BookContent(
            onItemClicked = { id ->
            },
            resourceBook = vm.bookApiData.value.searchData,
            onRetry = {},
        )
    }
}