package com.app.assignmentapp.data.remote.network

import com.squareup.picasso.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    const val HEADER_X_RAPID_KEY = "X-RapidAPI-Key"
    const val HEADER_HOST = "X-RapidAPI-Host"

    val BASE_URL = "https://imdb8.p.rapidapi.com/"

    private const val TIMEOUT = 30L // in seconds



    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(LoggingInterceptor())
        .addInterceptor(BasicAuthInterceptor())
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
               HttpLoggingInterceptor.Level.NONE
            } else HttpLoggingInterceptor.Level.NONE
        })
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS) //Backend is really slow
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()


    class BasicAuthInterceptor () : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val requestBuilder = chain.request().newBuilder()
                .addHeader(HEADER_X_RAPID_KEY, "arGiw6O4PtmshRnu6VGgrcJiO3DJp1cuMVojsnEABg5WRSaZVu")
                .addHeader(HEADER_HOST, "imdb8.p.rapidapi.com")
            return chain.proceed(requestBuilder.build())
        }
    }
}