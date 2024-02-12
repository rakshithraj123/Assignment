package com.app.assignmentapp.data

import com.app.assignmentapp.data.remote.NetworkRepository
import com.app.assignmentapp.data.remote.request.LoginRequest
import com.app.assignmentapp.model.MovieNameResponse

import javax.inject.Inject


class Repository @Inject constructor(
    private val networkRepository: NetworkRepository,
)  {

    suspend fun submitMovieName(loginRequest: LoginRequest): MovieNameResponse {
        return networkRepository.submitMovieName(loginRequest)
    }

}
