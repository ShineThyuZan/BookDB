package com.po.bookDB.ui.books

import com.po.bookDB.ui.data.local.BookDao
import com.po.bookDB.ui.model.BookEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavListRepositoryImpl @Inject constructor(
    private val dao: BookDao,
): FavListRepository {
    override fun getFavList(): Flow<List<BookEntity>> {
        return dao.getAllFavoriteBooks()
    }

}
