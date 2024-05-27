package com.po.bookDB.ui.auth

import com.po.bookDB.ui.common.DataResult
import com.po.bookDB.ui.data.local.BookDatabase
import com.po.bookDB.ui.model.BookEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavBookRepository @Inject constructor(
    database: BookDatabase
) {
    private val bookDao = database.bookDao()
    suspend fun insertBook(
       book: BookEntity
    ): DataResult<Boolean> = withContext(Dispatchers.IO) {

        bookDao.insertIntoFavorites(book)
        return@withContext DataResult.Success(true)
    }
}