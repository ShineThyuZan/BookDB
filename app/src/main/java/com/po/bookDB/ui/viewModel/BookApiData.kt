package com.po.bookDB.ui.viewModel

import com.po.bookDB.ui.common.Resource
import com.po.bookDB.ui.model.BookEntity

data class BookApiData(
    val books: Resource<List<BookEntity>> = Resource.Loading(),
    val searchData : Resource<List<BookEntity>> = Resource.Loading(),
    val favList : List<BookEntity> = emptyList()
)
