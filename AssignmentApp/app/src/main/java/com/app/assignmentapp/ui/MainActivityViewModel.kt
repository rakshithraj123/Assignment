package com.app.assignmentapp.ui

import android.content.Context
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.assignmentapp.R
import com.app.assignmentapp.data.Repository
import com.app.assignmentapp.data.remote.network.NetworkResult
import com.app.assignmentapp.data.remote.network.exceptions.ErrorResponseException
import com.app.assignmentapp.data.remote.request.LoginRequest
import com.app.assignmentapp.model.MovieNameResponse
import com.app.assignmentapp.utils.NetworkUtils

import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch

import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: Repository
) : ViewModel() {

    val loading = MutableLiveData<Boolean>()
    var movieName: String = "";
    var response: MutableLiveData<NetworkResult<MovieNameResponse>> = MutableLiveData()

    /**
     * submit movie name
     */
    fun submitMovieName() {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            response.value = NetworkResult.Error(context.getString(R.string.no_network))
            return
        }
        if (TextUtils.isEmpty(movieName)) {
            response.value = NetworkResult.Error(context.getString(R.string.enter_movie_name))
            return
        }
        viewModelScope.launch {
            try {
                loading.postValue(true)
                var movieResponse = repository.submitMovieName(LoginRequest(movieName))
                loading.postValue(false)
                response.value = NetworkResult.Success(movieResponse)
            } catch (e: ErrorResponseException) {
                loading.postValue(false)
                response.value = NetworkResult.Error(e.message)
            }finally {
                movieName = ""
            }
        }
    }
}