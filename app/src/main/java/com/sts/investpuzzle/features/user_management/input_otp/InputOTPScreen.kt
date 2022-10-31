package com.sts.investpuzzle.features.user_management.input_otp

import `in`.aabhasjindal.otptextview.OTPListener
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sts.investpuzzle.R
import com.sts.investpuzzle.base.BaseFragment
import com.sts.investpuzzle.constants.COMMON_ERROR_TITLE
import com.sts.investpuzzle.constants.OTP_WRONG_ERROR_MESSAGE
import com.sts.investpuzzle.constants.OTP_WRONG_ERROR_TITLE
import com.sts.investpuzzle.databinding.ScreenInputOtpBinding
import com.sts.investpuzzle.extensions.observeEvent
import com.sts.investpuzzle.features.user_management.input_user_detail.InputUserInfoScreen
import com.sts.investpuzzle.features.user_management.reset_password.ResetPasswordScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputOTPScreen : BaseFragment<InputOTPScreenViewModel, ScreenInputOtpBinding>(){

    companion object {
        const val SIGNUP_ARG = "signup_arg"
        const val FORGOT_PASSWORD_ARG = "forgot_password_arg"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        setupViewBinding(ScreenInputOtpBinding.inflate(inflater, container, false))
        withViewModel<InputOTPScreenViewModel> {
            observeEvent(timer_5_min) { viewBind.txvTimer.text = it }
        }

        return viewBind.root
    }

    @SuppressLint("SetTextI18n")
    override fun setUp() {
        viewBind.txvSentTo.text = "${getString(R.string.sent_to)} ${viewModel.signupResponse?.email}"
        viewBind.txvResendCode.setOnClickListener(this)
        viewBind.backButton.setOnClickListener(this)
        viewBind.txvEmailVerify.setOnClickListener(this)
        viewBind.otpView.otpListener = object : OTPListener {
            override fun onInteractionListener() {}

            override fun onOTPComplete(otp: String) {
                if (viewModel.signupResponse != null){
                    gotoInputUserInfoScreen(otp)
                }else if (viewModel.forgotPasswordResponse != null){
                    gotoResetPasswordScreen(otp)
                }

            }
        }
    }

    private fun gotoInputUserInfoScreen (otpCode : String){
        if (TextUtils.equals(otpCode, viewModel.signupResponse?.code ?: "")){
            val bundle = Bundle()
            bundle.apply { putParcelable(InputUserInfoScreen.SCREEN_ARG, viewModel.signupResponse) }

            viewModel.stopTimer()
            navigationService.openInputUserInfoScreen(bundle)
        }else
            showError(OTP_WRONG_ERROR_TITLE, OTP_WRONG_ERROR_MESSAGE)
    }

    private fun gotoResetPasswordScreen(otpCode: String){
        if (TextUtils.equals(otpCode, viewModel.forgotPasswordResponse?.data?.code ?: "")) {
            val bundle = Bundle()
            bundle.apply { putParcelable(ResetPasswordScreen.RESET_SCREEN_ARG, viewModel.forgotPasswordResponse) }
            viewModel.stopTimer()
            navigationService.openResetScreen(bundle)
        } else
            showError(OTP_WRONG_ERROR_TITLE, OTP_WRONG_ERROR_MESSAGE)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.backButton -> {
                navigationService.goBack()
            }
            R.id.txvEmailVerify -> {
                if (viewBind.otpView.otp?.length == 6){
                    gotoInputUserInfoScreen(viewBind.otpView.otp!!)
                }
            }
            R.id.txvResendCode -> {
                viewModel.resendCode()
            }
        }
    }
}