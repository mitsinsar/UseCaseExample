package com.mitsinsar.mvvmexample.model.api

import com.google.gson.annotations.SerializedName


data class UserListResponse(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("per_page") var perPage: Int? = null,
    @SerializedName("total") var total: Int? = null,
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("data") var userList: List<UserResponse>? = null
)
