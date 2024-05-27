package com.po.bookDB.ui.network

import android.content.Context
import androidx.room.Room
import com.po.bookDB.BuildConfig
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.po.bookDB.ui.common.Constants
import com.po.bookDB.ui.common.Endpoints
import com.po.bookDB.ui.data.local.BookDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideBookApiService(
        @QualifiedAnnotation.BookRetrofit bookRetrofit: Retrofit
    ): BookApiService {
        return bookRetrofit.create(BookApiService::class.java)
    }


    // book base url
    @Provides
    @Singleton
    @QualifiedAnnotation.BookRetrofit
    fun provideMoviesRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.dbooks.org/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

////////////////////////////////////////////////////////////////////////////////////////////////////

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return if (BuildConfig.DEBUG) {
            //this is for logging profiler
            OkHttpClient.Builder()
                .addInterceptor(OkHttpProfilerInterceptor())
                .addNetworkInterceptor(OkHttpProfilerInterceptor())
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()

        } else OkHttpClient
            .Builder()
            .build()
    }
}

