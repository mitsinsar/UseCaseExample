package com.mitsinsar.mvvmexample.model.ui

data class UserDetail(
    val id: Int,
    val email: String,
    val name: String,
    val lastName: String
) {

    val fullName: String
        get() = "$name $lastName"
}
