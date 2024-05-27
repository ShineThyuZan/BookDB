package com.po.bookDB.ui.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.po.bookDB.ui.model.BookEntity
import com.po.bookDB.ui.model.UserEntity

@Database(
    entities = [UserEntity::class, BookEntity::class],
    version = 3,
    exportSchema = false
)
abstract class BookDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun userDataDao() : UserDataDao
    abstract fun bookDao(): BookDao
    companion object {
        const val DB_NAME = "book_db"
    }
}