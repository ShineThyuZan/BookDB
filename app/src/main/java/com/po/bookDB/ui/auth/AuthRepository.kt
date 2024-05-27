package com.po.bookDB.ui.auth

import com.po.bookDB.ui.common.DataResult
import com.po.bookDB.ui.data.local.BookDatabase
import com.po.bookDB.ui.data.local.UserDao
import com.po.bookDB.ui.model.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val database: BookDatabase
) {
    val currentUser: String = ""
    private val userDao = database.userDao()
    suspend fun createUser(
        email: String,
        password: String
    ): DataResult<Boolean> = withContext(Dispatchers.IO) {
        val user = userDao.getUserByName(userName = email)
        if (user != null) {
            return@withContext DataResult.Error(
                code = 111,
                error = "Already Register!"
            )
        } else {
            userDao.insertUser(
                UserEntity(
                    userName = email,
                    password = password,
                    isLogin = false
                )
            )
            return@withContext DataResult.Success(true)
        }
    }

    suspend fun login(
        email: String,
        password: String
    ): DataResult<Boolean> = withContext(Dispatchers.IO) {
        val user = userDao.getUserByName(email)
        if (user != null) {
            if (user.password == password) {
                userDao.logoutAllAndLogin(email)
                return@withContext DataResult.Success(true)
            } else {
                return@withContext DataResult.Error(
                    code = 111,
                    error = "Username and password doesn't match!"
                )
            }
        } else {
            return@withContext DataResult.Error(
                code = 111,
                error = "No user found!"
            )
        }
    }

    fun hasUser(): Boolean {
        return true
    }
}