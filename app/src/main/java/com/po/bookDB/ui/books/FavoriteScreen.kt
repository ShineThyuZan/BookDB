package com.po.bookDB.ui.books

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.po.bookDB.R
import com.po.bookDB.ui.common.CustomVerticalSpacer
import com.po.bookDB.ui.viewModel.BookEvent
import com.po.bookDB.ui.viewModel.BookFavAction
import com.po.bookDB.ui.viewModel.BookViewModel


@Composable
fun FavoriteScreen(
    navToBookPage: () -> Unit,
) {
    val vm = hiltViewModel<BookViewModel>()
    BookView(
        vm = vm,
        navToBookPage = navToBookPage,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookView(
    vm: BookViewModel,
    navToBookPage: () -> Unit,
) {

    LaunchedEffect(key1 = true) {
        vm.favList()
        vm.bookEvent.collect {
            when (it) {
                is   BookEvent.SaveFavBook -> {
                    // todo
                }
                is BookEvent.ShowSnackBar -> {

                }
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Favorite Page",
                    color = Color.White ) },
                navigationIcon = {
                    IconButton(onClick = {
                        navToBookPage()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_cross),
                            contentDescription = "Menu Icon"
                        )
                    }
                },
                actions = {}
            )
        },
    ) {
        Modifier.padding(it)

        Column(
            modifier = Modifier
                .wrapContentHeight().padding(top = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            BookFavListView(
                book = vm.bookApiData.value.favList,
            )
        }

    }
}