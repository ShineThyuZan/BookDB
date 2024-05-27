package com.po.bookDB.ui.model

import kotlinx.serialization.Serializable

 @Serializable
data class BookDTO(
     val status: String,
     val total : String,
     val books: List<BookData>,
)

@Serializable
data class BookData(
    val id: String,
    val title: String,
    val subtitle: String,
    val authors: String,
    val image: String,
    val url: String,

) {
    fun toVo(): BookEntity {
        return BookEntity(
            bookId = id,
            bookTitle = title,
            bookSubtitle = subtitle,
            authors = authors,
            bookImage = image,
            bookUrl = url,
        )
    }
}