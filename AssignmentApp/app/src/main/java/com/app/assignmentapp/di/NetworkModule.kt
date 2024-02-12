package com.app.assignmentapp.di

import android.R.attr.password
import com.app.assignmentapp.data.remote.network.RetrofitAPIService
import com.app.assignmentapp.data.remote.network.RetrofitInstance
import com.app.assignmentapp.data.remote.network.RetrofitInstance.HEADER_HOST
import com.app.assignmentapp.data.remote.network.RetrofitInstance.HEADER_X_RAPID_KEY
import com.app.assignmentapp.data.remote.network.RetrofitInstance.okHttpClient
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
open class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofitAPIService(): RetrofitAPIService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(RetrofitInstance.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().apply { setLenient() }.create()))
            .build().create(RetrofitAPIService::class.java)
    }
}