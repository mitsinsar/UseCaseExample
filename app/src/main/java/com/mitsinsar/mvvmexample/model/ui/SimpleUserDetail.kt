package com.mitsinsar.mvvmexample.model.ui

data class SimpleUserDetail(
    val userId: Int,
    val name: String,
    val lastName: String
) {

    val fullName: String
        get() = "$name $lastName"
}
