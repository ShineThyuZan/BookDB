package com.po.bookDB.ui.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
data class BookEntity(
    @PrimaryKey val bookId: String,
    val bookTitle: String,
    val bookSubtitle: String,
    val authors: String,
    val bookImage: String,
    val bookUrl: String,
)

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val userName: String,
    val password: String,
    val isLogin: Boolean = false
)
