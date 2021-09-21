package com.chetocoders.chetogames.data.server

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
                builder.header(ServerConstants.HEADER_CLIENT_ID, CLIENT_ID)
                builder.header(ServerConstants.HEADER_AUTHORIZATION, AUTHORIZATION)

                return@Interceptor chain.proceed(builder.build())
            }).build()
        }

    val service: GamesServerService = Retrofit.Builder()
        .baseUrl(ServerConstants.URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .run {
            create(GamesServerService::class.java)
        }
}