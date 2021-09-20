package com.chetocoders.chetogames.data.server

import android.provider.Settings.Global.getString
import com.chetocoders.chetogames.R
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GamesServer {

    private val okHttpClient =
        HttpLoggingInterceptor().run {
            level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
                val builder = chain.request().newBuilder()
                builder.header("Client-ID", ApiConstants.CLIENT_ID)
                builder.header("Authorization", ApiConstants.AUTHORIZATION)

                return@Interceptor chain.proceed(builder.build())
            }).build()
        }

    val service: GamesServerService = Retrofit.Builder()
        .baseUrl(ApiConstants.URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .run {
            create(GamesServerService::class.java)
        }
}