package com.mitsinsar.mvvmexample.utils

sealed class Resource<out T> {

    data class Success<T>(val data: T?) : Resource<T>()
    data class Error(val exception: Throwable) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
    object OnLoadingFinished : Resource<Nothing>()

    fun use(
        onSuccess: ((T) -> Unit)? = null,
        onFailed: ((Error) -> Unit)? = null,
        onLoading: (() -> Unit)? = null,
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
                onFailed?.invoke(this)
            }
            is Loading -> {
                onLoading?.invoke()
            }
            is OnLoadingFinished -> {
                onLoadingFinished?.invoke()
            }
        }
    }
}
