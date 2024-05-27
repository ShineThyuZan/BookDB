package com.po.bookDB.ui.viewModel

import com.po.bookDB.ui.model.BookEntity

sealed class BookFavAction {
    data class ClickFav(val bookObj : BookEntity) : BookFavAction()}