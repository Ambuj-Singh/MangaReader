package com.android.test.mangareader.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.test.mangareader.data.model.StandardError
import com.android.test.mangareader.services.RestClient
import com.android.test.mangareader.utils.CustomLogger
import okio.IOException
import retrofit2.HttpException
import java.net.SocketTimeoutException

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var noInternetConnection: MutableLiveData<Boolean>

    lateinit var showLoader: MutableLiveData<Boolean>

    lateinit var message: MutableLiveData<String>

    lateinit var unAuthorized: MutableLiveData<Boolean>

    init {
        init()
    }

    private fun init() {
        noInternetConnection = MutableLiveData()
        showLoader = MutableLiveData()
        message = MutableLiveData()
        showLoader = MutableLiveData()
    }

    override fun onCleared() {
        super.onCleared()
        CustomLogger.log("View Model Cleared")
    }

    fun parseError(e: Throwable): String? {
        return if (e is HttpException) {
            try {
                val responseBodyObjectConverter =
                    RestClient.getRetrofit().responseBodyConverter<StandardError>(
                        StandardError::class.java,
                        arrayOfNulls(0)
                    )
                val error = responseBodyObjectConverter.convert(
                    e.response()!!.errorBody()!!
                )
                error!!.message
            } catch (exception: Exception) {
                exception.message
            }
        } else if (e is SocketTimeoutException) {
            "Connect to your network and try again"

        } else if (e is IOException) {
            if (e.message == null) "Some error occurred" + e.localizedMessage else e.message
        } else {
            if (e.message == null) "Some error occurred" + e.localizedMessage else e.message
        }
    }
}