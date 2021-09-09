package com.mitsinsar.mvvmexample.ui.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitsinsar.mvvmexample.usecase.UserListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userListUseCase: UserListUseCase
) : ViewModel() {

    val userListStateFlow
        get() = userListUseCase.userListStateFlow

    init {
        getUserList()
    }

    private fun getUserList() {
        viewModelScope.launch(Dispatchers.IO) {
            userListUseCase.getUserList()
        }
    }
}
