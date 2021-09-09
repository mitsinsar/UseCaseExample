package com.mitsinsar.mvvmexample.model.mapper

import com.mitsinsar.mvvmexample.model.api.UserListResponse
import com.mitsinsar.mvvmexample.model.ui.Paginated
import com.mitsinsar.mvvmexample.model.ui.SimpleUserDetail
import javax.inject.Inject

class UserListMapper @Inject constructor(
    private val simpleUserDetailMapper: SimpleUserDetailMapper
) : EntityMapper<Paginated<List<SimpleUserDetail>>, UserListResponse> {

    override fun mapToEntity(dto: UserListResponse): Paginated<List<SimpleUserDetail>> {
        val userList = dto.userList?.mapNotNull { simpleUserDetailMapper.mapToEntity(it) }.orEmpty()
        return Paginated(
            page = dto.page ?: 1,
            perPage = dto.perPage ?: 1,
            total = dto.total ?: 0,
            totalPages = dto.totalPages ?: 1,
            data = userList
        )
    }
}
