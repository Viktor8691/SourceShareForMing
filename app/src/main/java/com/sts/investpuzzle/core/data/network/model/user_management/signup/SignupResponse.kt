package com.sts.investpuzzle.core.data.network.model.user_management.signup

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignupResponse(
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: SignupData,
    @SerializedName("code") var code: String,
    var email : String,
    var password : String,
    var username : String
): Parcelable

@Parcelize
data class SignupData (
    @SerializedName("jwt") val jwt: String,
) : Parcelable
