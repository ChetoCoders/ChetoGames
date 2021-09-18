package com.chetocoders.chetogames.data.server

import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.internal.addHeaderLenient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GamesServer {

    private val okHttpClient =
        HttpLoggingInterceptor().run {
            addHeaderLenient(Headers.Builder(), "Client-ID", "h712tytpk4iihmymcjmg2xcse9kwhn")
            addHeaderLenient(Headers.Builder(), "Authorization", "Bearer ci7b3bnyn0t79lmr12x98l7yxukfng")
            level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder().addInterceptor(this).build()
        }

    val service: GamesServerService = Retrofit.Builder()
        .baseUrl("https://api.igdb.com/v4/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .run {
            create(GamesServerService::class.java)
        }
}