package com.mitsinsar.mvvmexample.model.api

import com.google.gson.annotations.SerializedName

data class UserDetailResponse(
    @SerializedName("data") val userResponse: UserResponse? = null
)
