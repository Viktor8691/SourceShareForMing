package com.sts.investpuzzle.features.user_management.reset_password

import com.sts.investpuzzle.base.BaseInteractor
import com.sts.investpuzzle.core.data.network.model.user_management.reset_password.ResetPasswordRequest
import com.sts.investpuzzle.core.data.network.model.user_management.signin.SignInResponse
import com.sts.investpuzzle.core.data.network.repository.AuthRepository
import com.sts.investpuzzle.core.data.prefs.PreferencesHelper
import com.sts.investpuzzle.core.data.session.SessionHelper
import io.reactivex.Single
import javax.inject.Inject


class ResetPasswordScreenInteractor @Inject internal constructor(
    preferencesHelper: PreferencesHelper,
    sessionHelper: SessionHelper,
    private val authRepository: AuthRepository
) : BaseInteractor(preferencesHelper, sessionHelper){

    fun resetPassword(resetPasswordRequest: ResetPasswordRequest) : Single<SignInResponse>{
        return authRepository.resetPassword(resetPasswordRequest)
    }
}