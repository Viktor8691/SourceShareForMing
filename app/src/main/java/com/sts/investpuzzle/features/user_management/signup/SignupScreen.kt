package com.sts.investpuzzle.features.user_management.signup

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sts.investpuzzle.R
import com.sts.investpuzzle.base.BaseFragment
import com.sts.investpuzzle.databinding.ScreenSignupBinding
import com.sts.investpuzzle.extensions.observeEvent
import com.sts.investpuzzle.features.user_management.input_otp.InputOTPScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupScreen : BaseFragment<SignupScreenViewModel, ScreenSignupBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupViewBinding(ScreenSignupBinding.inflate(inflater, container, false))
        withViewModel<SignupScreenViewModel> {
            observeEvent(signupResponse) {
                val bundle = Bundle()
                bundle.apply { putParcelable(InputOTPScreen.SIGNUP_ARG, it) }
                navigationService.openInputOTPScreen(bundle)
            }
        }
        return viewBind.root
    }

    override fun setUp() {
        viewBind.backButton.setOnClickListener(this)
        viewBind.txvContinue.setOnClickListener(this)
    }

    private fun checkFormValidate() : Boolean {
        if (viewBind.edtEmail.text.isEmpty()) {
            showError(getString(R.string.error), getString(R.string.input_email))
            return false
        }
        if (viewBind.edtUsername.text.isEmpty()){
            showError(getString(R.string.error), getString(R.string.input_username))
            return false
        }
        if (viewBind.edtPwd.text.isEmpty()){
            showError(getString(R.string.error), getString(R.string.input_password))
            return false
        }
        if (!TextUtils.equals(viewBind.edtPwd.text, viewBind.edtConfirmPwd.text)){
            showError(getString(R.string.error), getString(R.string.please_confirm_your_password))
            return false
        }
        return true
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.backButton -> {
                navigationService.goBack()
            }
            R.id.txvContinue -> {
                if (checkFormValidate())
                    viewModel.signup(
                        viewBind.edtEmail.text.toString(),
                        viewBind.edtUsername.text.toString(),
                        viewBind.edtPwd.text.toString())
            }
        }
    }
}