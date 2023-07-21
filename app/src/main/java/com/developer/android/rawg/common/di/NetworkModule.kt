package com.developer.android.rawg.common.di

import android.content.Context
import androidx.room.Room
import com.developer.android.rawg.main.api.RawgApi
import com.developer.android.rawg.main.db.RawgDatabase
import com.developer.android.rawg.main.db.dao.GamesDao
import com.developer.android.rawg.main.db.dao.GenresDao
import com.developer.android.rawg.utils.Utils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): RawgApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(RawgApi::class.java)
    }

    @Provides
    @Singleton
    fun context(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    internal fun providesDb(context: Context): RawgDatabase {
        return Room.databaseBuilder(context, RawgDatabase::class.java, "rawgDB")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesGenresDao(db: RawgDatabase): GenresDao {
        return db.getGenresDao()
    }

    @Provides
    @Singleton
    fun providesGamesDao(db: RawgDatabase): GamesDao {
        return db.getGamesDao()
    }

}