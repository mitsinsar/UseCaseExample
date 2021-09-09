package com.mitsinsar.mvvmexample.utils

sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    suspend fun use(onSuccess: (suspend (T) -> Unit)? = null, onFailed: (suspend (Exception) -> Unit)? = null) {
        when (this) {
            is Success -> onSuccess?.invoke(data)
            is Error -> onFailed?.invoke(exception)
        }
    }

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}
