package com.mitsinsar.mvvmexample.utils

import retrofit2.Response
import java.io.IOException

suspend fun <T : Any> safeApiCall(call: suspend () -> Result<T>): Result<T> {
    return try {
        call()
    } catch (e: Exception) {
        // An exception was thrown when calling the API so we're converting this to an IOException
        Result.Error(IOException(null, e))
    }
}

suspend fun <T : Any> request(
    onFailed: ((Response<T>) -> Result<T>)? = null,
    doRequest: suspend () -> Response<T>
): Result<T> {
    return safeApiCall {
        with(doRequest()) {
            if (isSuccessful && body() != null) {
                Result.Success(body() as T)
            } else {
                onFailed?.invoke(this) ?: Result.Error(Exception(errorBody().toString()))
            }
        }
    }
}
