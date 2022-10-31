package com.sts.investpuzzle.features.ranking

import com.sts.investpuzzle.base.BaseInteractor
import com.sts.investpuzzle.core.data.prefs.PreferencesHelper
import com.sts.investpuzzle.core.data.session.SessionHelper
import javax.inject.Inject


class RankingScreenInteractor @Inject internal constructor(
    preferencesHelper: PreferencesHelper,
    sessionHelper: SessionHelper
) : BaseInteractor(preferencesHelper, sessionHelper){
}