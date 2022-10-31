package com.sts.investpuzzle.features.user_management.forgot_password

import com.sts.investpuzzle.base.BaseInteractor
import com.sts.investpuzzle.core.data.network.model.user_management.forgot_password.ForgotPasswordResponse
import com.sts.investpuzzle.core.data.network.repository.AuthRepository
import com.sts.investpuzzle.core.data.prefs.PreferencesHelper
import com.sts.investpuzzle.core.data.session.SessionHelper
import io.reactivex.Single
import javax.inject.Inject


class ForgotPwdScreenInteractor @Inject internal constructor(
    preferencesHelper: PreferencesHelper,
    sessionHelper: SessionHelper,
    private val authRepository: AuthRepository
) : BaseInteractor(preferencesHelper, sessionHelper){

    fun forgotPassword(email : String) : Single<ForgotPasswordResponse>{
        return authRepository.forgotPassword(email)
    }
}