package com.mitsinsar.mvvmexample.usecase

import com.mitsinsar.mvvmexample.model.api.UserListResponse
import com.mitsinsar.mvvmexample.model.mapper.UserListMapper
import com.mitsinsar.mvvmexample.model.ui.Paginated
import com.mitsinsar.mvvmexample.model.ui.SimpleUserDetail
import com.mitsinsar.mvvmexample.repository.UserRepository
import com.mitsinsar.mvvmexample.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class UserListUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val userListMapper: UserListMapper
) {

    private val _userListStateFlow =
        MutableStateFlow<Resource<Paginated<List<SimpleUserDetail>>>>(Resource.Loading)
    val userListStateFlow: StateFlow<Resource<Paginated<List<SimpleUserDetail>>>>
        get() = _userListStateFlow

    suspend fun getUserList() {
        userRepository.getUserList().use(
            onSuccess = ::onGetUserListSuccess,
            onFailed = ::onGetUserListFailed
        )
    }

    private suspend fun onGetUserListSuccess(userListResponse: UserListResponse) {
        val paginatedSimpleUserList = userListMapper.mapToEntity(userListResponse)
        _userListStateFlow.emit(Resource.Success(paginatedSimpleUserList))
    }

    private suspend fun onGetUserListFailed(exception: Exception) {
        _userListStateFlow.emit(Resource.Error(exception))
    }
}
