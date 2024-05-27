package com.po.bookDB.ui.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.po.bookDB.ui.model.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user_table WHERE :userName = userName")
    suspend fun getUserByName(userName: String): UserEntity?

//    @Query("SELECT * FROM user_table WHERE :userName = userName AND :password=password")
//    suspend fun isUserVerifyLogin(userName: String,password:String): UserEntity?
    @Query("UPDATE user_table SET isLogin = false")
    suspend fun logoutAllUsers()

    @Query("UPDATE user_table SET isLogin = true where :userName = userName")
    suspend fun login(userName: String)

    @Transaction
    suspend fun logoutAllAndLogin(userName: String) {
        logoutAllUsers()
        login(userName)
    }

}


@Dao
interface UserDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

    @Query("SELECT * FROM user_table")
    suspend fun getAllUsers(): List<UserEntity>
}