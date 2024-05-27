package com.po.bookDB.ui.books

import com.po.bookDB.ui.model.BookEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FavListUseCase @Inject constructor(
    private val repository: FavListRepository
) {
    operator fun invoke(): Flow<List<BookEntity>> {
        return repository.getFavList()
    }
}
