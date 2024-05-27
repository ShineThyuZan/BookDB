package com.po.bookDB.ui.auth

import com.po.bookDB.ui.common.DataResult
import com.po.bookDB.ui.model.BookEntity
import javax.inject.Inject

class FavBookInsertUseCase @Inject constructor(
    private val dsRepo : FavBookRepository
){
    suspend operator fun invoke(
        book : BookEntity
    ): DataResult< Boolean>{
        return dsRepo.insertBook(book)
    }
}