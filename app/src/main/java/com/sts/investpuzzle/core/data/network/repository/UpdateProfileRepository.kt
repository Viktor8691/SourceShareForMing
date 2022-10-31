package com.sts.investpuzzle.core.data.network.repository

import com.sts.investpuzzle.core.data.network.model.user_management.update_profile.UpdateProfileRequest
import com.sts.investpuzzle.core.data.network.model.user_management.update_profile.UpdateProfileResponse
import io.reactivex.Single

interface UpdateProfileRepository {
    fun updateProfile(updateProfileRequest: UpdateProfileRequest) : Single<UpdateProfileResponse>
}