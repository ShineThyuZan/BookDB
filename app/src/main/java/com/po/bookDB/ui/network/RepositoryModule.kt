package com.po.bookDB.ui.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthApiRepository(
        api: BookApiService,
        @QualifiedAnnotation.Io io: CoroutineDispatcher,

    ): BookRepository {
        return BookRepositoryImpl(
            api = api,
            io = io,
        )
    }
}