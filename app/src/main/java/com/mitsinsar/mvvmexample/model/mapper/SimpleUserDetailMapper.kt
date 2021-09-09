package com.mitsinsar.mvvmexample.model.mapper

import com.mitsinsar.mvvmexample.model.api.UserResponse
import com.mitsinsar.mvvmexample.model.ui.SimpleUserDetail
import javax.inject.Inject

class SimpleUserDetailMapper @Inject constructor() : EntityMapper<SimpleUserDetail?, UserResponse> {

    override fun mapToEntity(dto: UserResponse): SimpleUserDetail? {
        return SimpleUserDetail(
            userId = dto.id ?: return null,
            name = dto.firstName ?: return null,
            lastName = dto.lastName ?: return null
        )
    }
}
