package com.po.bookDB.ui.network

import com.po.bookDB.ui.data.local.UserDataDao
import com.po.bookDB.ui.model.BookDTO
import com.po.bookDB.ui.model.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface BookRepository {
    suspend fun fetchBook(query: String): Flow<RemoteResource<BookDTO>>
}



class UserDataRepository(private val userDao: UserDataDao) {
    suspend fun insert(user: UserEntity) {
        withContext(Dispatchers.IO) {
            userDao.insert(user)
        }
    }

    suspend fun getAllUsers(): List<UserEntity> {
        return withContext(Dispatchers.IO) {
            userDao.getAllUsers()
        }
    }
}
