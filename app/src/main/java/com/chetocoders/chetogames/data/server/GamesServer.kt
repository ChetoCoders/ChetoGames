package com.chetocoders.chetogames.data.server

import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.addHeaderLenient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GamesServer {

    //TODO() refactorizar las claves en un fichero
    private val okHttpClient =
        HttpLoggingInterceptor().run {
            level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
                val builder = chain.request().newBuilder()

                // TODO: Pass values to constants
                builder.header("Client-ID", "h712tytpk4iihmymcjmg2xcse9kwhn")
                builder.header("Authorization", "Bearer ci7b3bnyn0t79lmr12x98l7yxukfng")
                val request = chain.request()

                return@Interceptor chain.proceed(builder.build())
            }).build()
        }

    val service: GamesServerService = Retrofit.Builder()
        // TODO: Pass url to constants
        .baseUrl("https://api.igdb.com/v4")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .run {
            create(GamesServerService::class.java)
        }
}