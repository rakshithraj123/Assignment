package com.app.assignmentapp.data.remote

import com.app.assignmentapp.data.remote.network.RetrofitAPIService
import com.app.assignmentapp.data.remote.request.LoginRequest
import com.app.assignmentapp.model.MovieNameResponse
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val retrofitAPIService: RetrofitAPIService)  :
    NetworkRepository {
    override suspend fun submitMovieName(loginRequest: LoginRequest): MovieNameResponse {
        return retrofitAPIService.submitMovieName(loginRequest.movieName)
    }
}