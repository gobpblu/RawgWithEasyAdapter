package com.developer.android.rawg.common

import com.developer.android.rawg.main.api.RawgApi
import com.developer.android.rawg.utils.Utils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CommonModule {
    fun createRetrofit() = module {
        single {
            val interceptor =  HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(RawgApi::class.java)
        }
    }
}