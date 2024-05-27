package com.po.bookDB.ui.books

import com.po.bookDB.ui.model.BookDTO
import com.po.bookDB.ui.model.BookEntity
import kotlinx.coroutines.flow.Flow


interface FavListRepository {
    fun getFavList(): Flow<List<BookEntity>>
}
