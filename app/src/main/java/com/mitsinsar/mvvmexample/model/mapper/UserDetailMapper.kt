package com.mitsinsar.mvvmexample.model.mapper

import com.mitsinsar.mvvmexample.model.api.UserDetailResponse
import com.mitsinsar.mvvmexample.model.ui.UserDetail
import javax.inject.Inject

class UserDetailMapper @Inject constructor() : EntityMapper<UserDetail?, UserDetailResponse> {

    override fun mapToEntity(dto: UserDetailResponse): UserDetail? {
        return dto.userResponse?.run {
            UserDetail(
                id = id ?: return null,
                email = email.orEmpty(),
                name = firstName.orEmpty(),
                lastName = lastName.orEmpty()
            )
        }
    }
}
