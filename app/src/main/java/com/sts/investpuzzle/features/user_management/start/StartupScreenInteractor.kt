package com.sts.investpuzzle.features.user_management.start

import com.sts.investpuzzle.base.BaseInteractor
import com.sts.investpuzzle.core.data.network.model.accessories.AccessoriesResponse
import com.sts.investpuzzle.core.data.network.repository.AccessoriesRepository
import com.sts.investpuzzle.core.data.prefs.PreferencesHelper
import com.sts.investpuzzle.core.data.session.SessionHelper
import io.reactivex.Single
import javax.inject.Inject


class StartupScreenInteractor @Inject internal constructor(
    preferencesHelper: PreferencesHelper,
    sessionHelper: SessionHelper,
    private val accessoriesRepository: AccessoriesRepository
) : BaseInteractor(preferencesHelper, sessionHelper){

    fun getAccessories() : Single<AccessoriesResponse> {
        return accessoriesRepository.getAccessories()
    }
}