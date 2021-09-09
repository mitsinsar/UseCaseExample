package com.mitsinsar.mvvmexample.ui.userdetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitsinsar.mvvmexample.usecase.UserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userDetailUseCase: UserDetailUseCase
) : ViewModel() {

    private val userId: Int = savedStateHandle["userId"]
        ?: throw IllegalArgumentException("404 user id")

    val userDetailStateFlow
        get() = userDetailUseCase.userDetailStateFlow

    init {
        getUserDetail()
    }

    private fun getUserDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.e("test", "user detail fetch call")
            userDetailUseCase.getUserDetailById(userId)
        }
    }
}
