package com.app.assignmentapp.data.remote.network


import com.app.assignmentapp.model.MovieNameResponse
import retrofit2.http.*


interface RetrofitAPIService {

    @GET("title/find")
    suspend fun submitMovieName(
        @Query("q") movieName: String?,
    ): MovieNameResponse
}