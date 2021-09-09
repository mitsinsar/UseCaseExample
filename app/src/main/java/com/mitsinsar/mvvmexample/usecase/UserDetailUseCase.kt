package com.mitsinsar.mvvmexample.usecase

import com.mitsinsar.mvvmexample.model.api.UserDetailResponse
import com.mitsinsar.mvvmexample.model.mapper.UserDetailMapper
import com.mitsinsar.mvvmexample.model.ui.UserDetail
import com.mitsinsar.mvvmexample.repository.UserRepository
import com.mitsinsar.mvvmexample.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class UserDetailUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val userDetailMapper: UserDetailMapper
) {

    private val _userDetailStateFlow = MutableStateFlow<Resource<UserDetail>>(Resource.Loading)
    val userDetailStateFlow: StateFlow<Resource<UserDetail>>
        get() = _userDetailStateFlow

    suspend fun getUserDetailById(userId: Int) {
        userRepository.getUserDetail(userId).use(
            onSuccess = ::onGetUserDetailSuccess,
            onFailed = ::onGetUserDetailFailed
        )
    }

    private suspend fun onGetUserDetailSuccess(userDetailResponse: UserDetailResponse) {
        val userDetail = userDetailMapper.mapToEntity(userDetailResponse)
        _userDetailStateFlow.emit(Resource.Success(userDetail))
    }

    private suspend fun onGetUserDetailFailed(exception: Exception) {
        _userDetailStateFlow.emit(Resource.Error(exception))
    }
}
