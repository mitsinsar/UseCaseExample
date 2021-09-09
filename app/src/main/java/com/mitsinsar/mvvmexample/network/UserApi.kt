package com.mitsinsar.mvvmexample.network

import com.mitsinsar.mvvmexample.model.api.UserDetailResponse
import com.mitsinsar.mvvmexample.model.api.UserListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users/")
    suspend fun getUserList(): Response<UserListResponse>

    @GET("users/{userId}")
    suspend fun getUserDetailById(@Path("userId") userId: Int): Response<UserDetailResponse>
}
