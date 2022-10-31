package com.sts.investpuzzle.core.data.network.repository

import com.sts.investpuzzle.core.data.network.model.user_management.forgot_password.ForgotPasswordResponse
import com.sts.investpuzzle.core.data.network.model.user_management.reset_password.ResetPasswordRequest
import com.sts.investpuzzle.core.data.network.model.user_management.signin.SignInRequest
import com.sts.investpuzzle.core.data.network.model.user_management.signin.SignInResponse
import com.sts.investpuzzle.core.data.network.model.user_management.signup.SignupRequest
import com.sts.investpuzzle.core.data.network.model.user_management.signup.SignupResponse
import com.sts.investpuzzle.core.data.network.model.user_management.user_info.UserInfoRequest
import com.sts.investpuzzle.core.data.network.model.user_management.user_info.UserInfoResponse
import io.reactivex.Single

interface AuthRepository {
    fun authenticateUser(signupRequest: SignupRequest) : Single<SignupResponse>
    fun uploadUserInfo(userInfoRequest: UserInfoRequest) : Single<UserInfoResponse>
    fun signIn(signInRequest: SignInRequest) : Single<SignInResponse>
    fun forgotPassword(email : String) : Single<ForgotPasswordResponse>
    fun resetPassword(resetPasswordRequest: ResetPasswordRequest) : Single<SignInResponse>
}