package com.sts.investpuzzle.features.user_management.reset_password

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sts.investpuzzle.R
import com.sts.investpuzzle.base.BaseFragment
import com.sts.investpuzzle.databinding.ScreenForgotPasswordBinding
import com.sts.investpuzzle.databinding.ScreenResetPasswordBinding
import com.sts.investpuzzle.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPasswordScreen : BaseFragment<ResetPasswordScreenViewModel, ScreenResetPasswordBinding>(){

    companion object {
        const val RESET_SCREEN_ARG = "signup_arg"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        setupViewBinding(ScreenResetPasswordBinding.inflate(inflater, container, false))

        withViewModel<ResetPasswordScreenViewModel> {
            observeEvent(viewModel.signInRequest){
                navigationService.openResetPwdSuccessScreen()
            }
        }

        return viewBind.root
    }

    override fun setUp() {
        viewBind.txvChangePassword.setOnClickListener(this)
        viewBind.backButton.setOnClickListener(this)
    }

    private fun isFormValidate() : Boolean {
        if (viewBind.edtPassword.text.isEmpty()){
            showError(getString(R.string.error), getString(R.string.input_password))
            return false
        }
        if (!TextUtils.equals(viewBind.edtPassword.text, viewBind.edtConfirmPwd.text)) {
            showError(getString(R.string.error), getString(R.string.confirm_password))
            return false
        }
        return true
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.txvChangePassword -> {
                if (isFormValidate()) viewModel.resetPassword(viewBind.edtPassword.text.toString())
            }
            R.id.backButton -> {
                navigationService.goBack()
            }
        }
    }
}