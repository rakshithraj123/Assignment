package com.app.assignmentapp.data.remote

import com.app.assignmentapp.data.remote.request.LoginRequest
import com.app.assignmentapp.model.MovieNameResponse


interface NetworkRepository {
    suspend fun submitMovieName(loginRequest: LoginRequest): MovieNameResponse
}