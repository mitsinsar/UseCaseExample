package com.mitsinsar.mvvmexample.ui.userlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mitsinsar.mvvmexample.R
import com.mitsinsar.mvvmexample.databinding.FragmentUserListBinding
import com.mitsinsar.mvvmexample.model.ui.Paginated
import com.mitsinsar.mvvmexample.model.ui.SimpleUserDetail
import com.mitsinsar.mvvmexample.ui.userlist.UserListFragmentDirections.Companion.actionUserListFragmentToUserDetailFragment
import com.mitsinsar.mvvmexample.utils.Resource
import com.mitsinsar.mvvmexample.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListFragment : Fragment(R.layout.fragment_user_list) {

    private val binding by viewBinding(FragmentUserListBinding::bind)

    private val userListViewModel: UserListViewModel by viewModels()

    private val userListAdapter = UserListAdapter(::onUserClick)

    private val userListCollector: suspend (Resource<Paginated<List<SimpleUserDetail>>>) -> Unit = {
        it.use(
            onSuccess = ::onGetUserListSuccess,
            onLoading = { binding.progressBar.visibility = View.VISIBLE },
            onLoadingFinished = { binding.progressBar.visibility = View.GONE }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        initUi()
    }

    private fun initUi() {
        binding.userListRecyclerView.adapter = userListAdapter
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            userListViewModel.userListStateFlow.collect(userListCollector)
        }
    }

    private fun onUserClick(simpleUserDetail: SimpleUserDetail) {
        findNavController().navigate(actionUserListFragmentToUserDetailFragment(simpleUserDetail.userId))
    }

    private fun onGetUserListSuccess(paginatedUserList: Paginated<List<SimpleUserDetail>>) {
        userListAdapter.submitList(paginatedUserList.data)
    }
}
