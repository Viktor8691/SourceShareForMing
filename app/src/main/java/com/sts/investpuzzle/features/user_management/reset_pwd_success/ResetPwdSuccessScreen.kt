package com.sts.investpuzzle.features.user_management.reset_pwd_success

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sts.investpuzzle.R
import com.sts.investpuzzle.base.BaseFragment
import com.sts.investpuzzle.databinding.ScreenResetPwdSuccessBinding
import com.sts.investpuzzle.features.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPwdSuccessScreen : BaseFragment<ResetPwdSuccessScreenViewModel, ScreenResetPwdSuccessBinding> (){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        setupViewBinding(ScreenResetPwdSuccessBinding.inflate(inflater, container, false))

        withViewModel<ResetPwdSuccessScreenViewModel> {  }

        return viewBind.root
    }

    override fun setUp() {
        viewBind.txvWelcomeBack.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.txvWelcomeBack -> {
                viewModel.signInStatus = true
                (activity as MainActivity).setUpHomeScreen()
            }
        }
    }
}