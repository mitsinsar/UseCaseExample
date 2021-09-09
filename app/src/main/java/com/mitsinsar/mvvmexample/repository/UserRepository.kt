package com.mitsinsar.mvvmexample.repository

import com.mitsinsar.mvvmexample.network.UserApi
import com.mitsinsar.mvvmexample.utils.request
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userApi: UserApi
) {

    suspend fun getUserList() = request {
        userApi.getUserList()
    }

    suspend fun getUserDetail(userId: Int) = request {
        userApi.getUserDetailById(userId)
    }
}
