package com.mitsinsar.mvvmexample.model.ui

data class Paginated<T>(
    val page: Int,
    val perPage: Int,
    val total: Int,
    val totalPages: Int,
    val data: T
)
