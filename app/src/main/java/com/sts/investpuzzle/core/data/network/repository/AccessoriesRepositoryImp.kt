package com.sts.investpuzzle.core.data.network.repository

import com.rx2androidnetworking.Rx2AndroidNetworking
import com.sts.investpuzzle.core.data.network.model.accessories.AccessoriesResponse
import com.sts.investpuzzle.core.data.session.SessionHelper
import io.reactivex.Single
import javax.inject.Inject

class AccessoriesRepositoryImp @Inject
internal constructor(private val sessionHelper: SessionHelper) : AccessoriesRepository {
    override fun getAccessories(): Single<AccessoriesResponse> {
        return Rx2AndroidNetworking.get(sessionHelper.apiEndPoint.getAccessories)
            .build()
            .getObjectSingle(AccessoriesResponse::class.java)
    }
}