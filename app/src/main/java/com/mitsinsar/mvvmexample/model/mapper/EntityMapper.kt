package com.mitsinsar.mvvmexample.model.mapper

interface EntityMapper<T, K> {

    fun mapToEntity(dto: K): T
}
