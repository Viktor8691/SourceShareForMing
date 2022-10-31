package com.sts.investpuzzle.core.data.network.repository

import com.rx2androidnetworking.Rx2AndroidNetworking
import com.sts.investpuzzle.core.data.network.model.user_management.update_profile.UpdateProfileRequest
import com.sts.investpuzzle.core.data.network.model.user_management.update_profile.UpdateProfileResponse
import com.sts.investpuzzle.core.data.session.SessionHelper
import io.reactivex.Single
import javax.inject.Inject


class UpdateProfileRepositoryImp @Inject
internal constructor(private val sessionHelper: SessionHelper) : UpdateProfileRepository{
    override fun updateProfile(updateProfileRequest: UpdateProfileRequest): Single<UpdateProfileResponse> {
        return Rx2AndroidNetworking.post(sessionHelper.apiEndPoint.updateProfile)
            .addHeaders(sessionHelper.authToken)
            .addBodyParameter(updateProfileRequest)
            .build()
            .getObjectSingle(UpdateProfileResponse::class.java)
    }
}