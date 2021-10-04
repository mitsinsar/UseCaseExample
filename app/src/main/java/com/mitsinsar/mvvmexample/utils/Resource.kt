package com.mitsinsar.mvvmexample.utils

sealed class Resource<out T> {

    data class Success<T>(val data: T?) : Resource<T>()
    data class Error<T>(val exception: Throwable, val data: T? = null) : Resource<T>()
    data class Loading<T>(val data: T? = null) : Resource<T>()
    object OnLoadingFinished : Resource<Nothing>()

    fun use(
        onSuccess: ((T) -> Unit)? = null,
        onFailed: ((Throwable, T?) -> Unit)? = null,
        onLoading: ((T?) -> Unit)? = null,
        onLoadingFinished: (() -> Unit)? = null
    ) {
        when (this) {
            is Success -> {
                onLoadingFinished?.invoke()
                if (data != null) {
                    onSuccess?.invoke(data)
                }
            }
            is Error -> {
                onLoadingFinished?.invoke()
                onFailed?.invoke(exception, data)
            }
            is Loading -> {
                onLoading?.invoke(data)
            }
            is OnLoadingFinished -> {
                onLoadingFinished?.invoke()
            }
        }
    }
}
