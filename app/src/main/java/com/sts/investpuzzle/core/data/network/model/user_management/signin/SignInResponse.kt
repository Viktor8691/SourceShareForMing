package com.sts.investpuzzle.core.data.network.model.user_management.signin

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sts.investpuzzle.core.data.network.model.accessories.Country
import com.sts.investpuzzle.core.data.network.model.accessories.Education
import com.sts.investpuzzle.core.data.network.model.accessories.Pronouns
import com.sts.investpuzzle.core.data.network.model.user_management.user_info.Profile
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignInResponse (
    @SerializedName("data") val data: SignInData,
): Parcelable

@Parcelize
data class SignInData(
    @SerializedName("user") val user : Profile
): Parcelable