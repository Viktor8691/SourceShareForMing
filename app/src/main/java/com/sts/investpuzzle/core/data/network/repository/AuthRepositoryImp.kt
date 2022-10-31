package com.sts.investpuzzle.core.data.network.repository

import com.rx2androidnetworking.Rx2AndroidNetworking
import com.sts.investpuzzle.core.data.network.model.user_management.forgot_password.ForgotPasswordResponse
import com.sts.investpuzzle.core.data.network.model.user_management.reset_password.ResetPasswordRequest
import com.sts.investpuzzle.core.data.network.model.user_management.signin.SignInRequest
import com.sts.investpuzzle.core.data.network.model.user_management.signin.SignInResponse
import com.sts.investpuzzle.core.data.network.model.user_management.signup.SignupRequest
import com.sts.investpuzzle.core.data.network.model.user_management.signup.SignupResponse
import com.sts.investpuzzle.core.data.network.model.user_management.user_info.UserInfoRequest
import com.sts.investpuzzle.core.data.network.model.user_management.user_info.UserInfoResponse
import com.sts.investpuzzle.core.data.session.SessionHelper
import io.reactivex.Single
import javax.inject.Inject

class AuthRepositoryImp @Inject
internal constructor(private val sessionHelper: SessionHelper) : AuthRepository{
    override fun authenticateUser(signupRequest: SignupRequest): Single<SignupResponse> {
        return Rx2AndroidNetworking.post(sessionHelper.apiEndPoint.authenticateUser)
            .addBodyParameter(signupRequest)
            .build()
            .getObjectSingle(SignupResponse::class.java)
    }

    override fun uploadUserInfo(userInfoRequest: UserInfoRequest): Single<UserInfoResponse> {
        return Rx2AndroidNetworking.post(sessionHelper.apiEndPoint.registerUser)
            .addHeaders(sessionHelper.authToken)
            .addBodyParameter(userInfoRequest)
            .build()
            .getObjectSingle(UserInfoResponse::class.java)
    }

    override fun signIn(signInRequest: SignInRequest): Single<SignInResponse> {
        return Rx2AndroidNetworking.post(sessionHelper.apiEndPoint.signInEndPoint)
            .addBodyParameter(signInRequest)
            .build()
            .getObjectSingle(SignInResponse::class.java)
    }

    override fun forgotPassword(email: String): Single<ForgotPasswordResponse> {
        return Rx2AndroidNetworking.post(sessionHelper.apiEndPoint.forgotPassword)
            .addBodyParameter(hashMapOf("email" to email))
            .build()
            .getObjectSingle(ForgotPasswordResponse::class.java)
    }

    override fun resetPassword(resetPasswordRequest: ResetPasswordRequest): Single<SignInResponse> {
        return Rx2AndroidNetworking.post(sessionHelper.apiEndPoint.resetPassword)
            .addHeaders(sessionHelper.authToken)
            .addBodyParameter(resetPasswordRequest)
            .build()
            .getObjectSingle(SignInResponse::class.java)
    }
}