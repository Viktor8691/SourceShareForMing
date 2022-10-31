package com.sts.investpuzzle.features.user_management.input_otp

import com.sts.investpuzzle.base.BaseInteractor
import com.sts.investpuzzle.core.data.network.model.user_management.forgot_password.ForgotPasswordResponse
import com.sts.investpuzzle.core.data.network.model.user_management.signup.SignupRequest
import com.sts.investpuzzle.core.data.network.model.user_management.signup.SignupResponse
import com.sts.investpuzzle.core.data.network.repository.AuthRepository
import com.sts.investpuzzle.core.data.prefs.PreferencesHelper
import com.sts.investpuzzle.core.data.session.SessionHelper
import io.reactivex.Single
import javax.inject.Inject

class InputOTPScreenInteractor @Inject internal constructor(
    preferencesHelper: PreferencesHelper,
    sessionHelper: SessionHelper,
    private val authRepository: AuthRepository
) : BaseInteractor(preferencesHelper, sessionHelper){

    fun resendSignupCode(signupRequest : SignupRequest) : Single<SignupResponse> {
        return authRepository.authenticateUser(signupRequest)
    }

    fun resendForgotPasswordCode(email : String) : Single<ForgotPasswordResponse>{
        return authRepository.forgotPassword(email)
    }

}