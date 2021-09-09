package com.mitsinsar.mvvmexample.ui.userdetail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mitsinsar.mvvmexample.R
import com.mitsinsar.mvvmexample.databinding.FragmentUserDetailBinding
import com.mitsinsar.mvvmexample.model.ui.UserDetail
import com.mitsinsar.mvvmexample.utils.Resource
import com.mitsinsar.mvvmexample.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserDetailFragment : Fragment(R.layout.fragment_user_detail) {

    private val binding by viewBinding(FragmentUserDetailBinding::bind)

    private val userDetailViewModel: UserDetailViewModel by viewModels()

    private val userDetailCollector: suspend (Resource<UserDetail>) -> Unit = {
        it.use(
            onSuccess = ::onGetUserDetailSuccess,
            onLoading = { binding.progressBar.visibility = View.VISIBLE },
            onLoadingFinished = { binding.progressBar.visibility = View.GONE }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            userDetailViewModel.userDetailStateFlow.collect(userDetailCollector)
        }
    }

    private fun onGetUserDetailSuccess(userDetail: UserDetail) {
        with(binding) {
            Log.e("test", "user detail observe call")
            userNameTextView.text = userDetail.fullName
            userEmailTextView.text = userDetail.email
        }
    }
}
