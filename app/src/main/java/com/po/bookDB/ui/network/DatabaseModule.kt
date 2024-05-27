package com.po.bookDB.ui.network

import android.content.Context
import androidx.room.Room
import com.po.bookDB.ui.books.FavListRepository
import com.po.bookDB.ui.books.FavListRepositoryImpl

import com.po.bookDB.ui.data.local.BookDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BookDatabase = Room.databaseBuilder(
        context,
        BookDatabase::class.java,
        BookDatabase.DB_NAME
    ).build()
    @Provides
    @Singleton
    fun provideRepository(
        db: BookDatabase,
    ): FavListRepository {
        return FavListRepositoryImpl(db.bookDao())
    }

}