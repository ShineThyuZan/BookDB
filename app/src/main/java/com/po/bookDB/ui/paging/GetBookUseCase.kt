package com.po.bookDB.ui.paging

import com.po.bookDB.ui.common.Resource
import com.po.bookDB.ui.model.BookEntity
import com.po.bookDB.ui.network.BookRepository
import com.po.bookDB.ui.network.RemoteResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
class GetBookUseCase @Inject constructor(
    private val apiRepo: BookRepository,
    ) {
    suspend operator fun invoke(query : String ): Flow<Resource<List<BookEntity>>> {
        val res = apiRepo.fetchBook(query)
        var bookList: Flow<Resource<List<BookEntity>>> = emptyFlow()

        res.collect {
            when (it) {
                is RemoteResource.ErrorEvent -> {
                    bookList = emptyFlow()
                }
                is RemoteResource.LoadingEvent -> {
                    bookList = flow {
                        emit(Resource.Loading())
                    }
                }
                is RemoteResource.SuccessEvent -> {
                    val data = Resource.Success(
                        data = it.data?.books?.map { bookData ->
                            bookData.toVo()
                        }?: emptyList()
                    )
                    bookList = flow {
                        emit(data)
                    }
                }
            }
        }
        return bookList
    }
}