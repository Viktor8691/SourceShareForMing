package com.sts.investpuzzle.features.user_management.input_user_detail

import com.sts.investpuzzle.base.BaseInteractor
import com.sts.investpuzzle.core.data.network.model.user_management.user_info.UserInfoRequest
import com.sts.investpuzzle.core.data.network.model.user_management.user_info.UserInfoResponse
import com.sts.investpuzzle.core.data.network.repository.AuthRepository
import com.sts.investpuzzle.core.data.prefs.PreferencesHelper
import com.sts.investpuzzle.core.data.session.SessionHelper
import io.reactivex.Single
import javax.inject.Inject

class InputUserInfoScreenInteractor @Inject internal constructor(
    preferencesHelper: PreferencesHelper,
    sessionHelper: SessionHelper,
    private val authRepository: AuthRepository
) : BaseInteractor(preferencesHelper, sessionHelper){

    fun uploadUserInfo(userInfoRequest: UserInfoRequest) : Single<UserInfoResponse>{
        return  authRepository.uploadUserInfo(userInfoRequest)
    }
}