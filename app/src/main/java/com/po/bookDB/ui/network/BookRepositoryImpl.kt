package com.po.bookDB.ui.network

import com.po.bookDB.ui.model.BookDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api: BookApiService,

    @QualifiedAnnotation.Io private val io: CoroutineDispatcher,
) : BookRepository {

    override suspend fun fetchBook(query: String): Flow<RemoteResource<BookDTO>> {
        return flow {
            emit(
                safeApiCall {
                    api.fetchBook(query = query)
                }
            )
        }.flowOn(io)
    }
}
