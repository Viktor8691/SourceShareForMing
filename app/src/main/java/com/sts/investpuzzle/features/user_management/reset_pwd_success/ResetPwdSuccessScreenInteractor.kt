package com.sts.investpuzzle.features.user_management.reset_pwd_success

import com.sts.investpuzzle.base.BaseInteractor
import com.sts.investpuzzle.core.data.prefs.PreferencesHelper
import com.sts.investpuzzle.core.data.session.SessionHelper
import javax.inject.Inject

class ResetPwdSuccessScreenInteractor @Inject internal constructor(
    preferencesHelper: PreferencesHelper,
    sessionHelper: SessionHelper,
) : BaseInteractor(preferencesHelper, sessionHelper){
}