package com.po.bookDB.ui.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.po.bookDB.ui.model.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntoFavorites(book : BookEntity)
    @Query("DELETE FROM book_table WHERE :id = bookId")
    suspend fun removeFromFavorites(id: String)

    @Query("DELETE FROM book_table")
    suspend fun deleteAllBooks()

   @Query("SELECT * FROM book_table")
    fun getAllFavoriteBooks(): Flow<List<BookEntity>>
}