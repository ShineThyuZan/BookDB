package com.po.bookDB.ui.network

import com.po.bookDB.ui.model.BookDTO
import retrofit2.Response
import retrofit2.http.*

interface BookApiService {
    @GET("api/search/{query}")
    suspend fun fetchBook(
        @Path("query") query : String
    ): Response<BookDTO>
}
