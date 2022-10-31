package com.sts.investpuzzle.features.user_management.signup

import com.sts.investpuzzle.base.BaseInteractor
import com.sts.investpuzzle.core.data.network.model.user_management.signup.SignupRequest
import com.sts.investpuzzle.core.data.network.model.user_management.signup.SignupResponse
import com.sts.investpuzzle.core.data.network.repository.AuthRepository
import com.sts.investpuzzle.core.data.prefs.PreferencesHelper
import com.sts.investpuzzle.core.data.session.SessionHelper
import io.reactivex.Single
import javax.inject.Inject

class SignupScreenInteractor @Inject internal constructor(
    preferencesHelper: PreferencesHelper,
    sessionHelper: SessionHelper,
    private val authRepository: AuthRepository
) : BaseInteractor(preferencesHelper, sessionHelper){

    fun signup(signupRequest : SignupRequest) : Single<SignupResponse> {
        return authRepository.authenticateUser(signupRequest)
    }
}