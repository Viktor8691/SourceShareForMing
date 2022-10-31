package com.sts.investpuzzle.features.user_management.forgot_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sts.investpuzzle.R
import com.sts.investpuzzle.base.BaseFragment
import com.sts.investpuzzle.databinding.ScreenForgotPasswordBinding
import com.sts.investpuzzle.extensions.observeEvent
import com.sts.investpuzzle.features.user_management.input_otp.InputOTPScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPwdScreen : BaseFragment<ForgotPwdScreenViewModel, ScreenForgotPasswordBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        setupViewBinding(ScreenForgotPasswordBinding.inflate(inflater, container, false))

        withViewModel<ForgotPwdScreenViewModel> {
            observeEvent(forgotPasswordResponse){
                it.email = viewBind.edtEmail.text.toString()
                val bundle = Bundle()
                bundle.apply { putParcelable(InputOTPScreen.FORGOT_PASSWORD_ARG, it) }
                navigationService.openInputOTPScreen(bundle)
            }
        }

        return viewBind.root
    }

    override fun setUp() {
        viewBind.txvSendCode.setOnClickListener(this)
        viewBind.backButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.backButton -> {
                navigationService.goBack()
            }
            R.id.txvSendCode -> {
                if (viewBind.edtEmail.text.isEmpty()){
                    showError(getString(R.string.error), getString(R.string.input_email))
                } else
                    viewModel.forgotPassword(viewBind.edtEmail.text.toString())
            }
        }
    }

}