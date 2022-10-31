package com.sts.investpuzzle.core.data.network.model.user_management.forgot_password

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForgotPasswordResponse (
    @SerializedName("message") val message : String,
    @SerializedName("data") val data : ForgotPasswordData,
    var email : String
) : Parcelable

@Parcelize
data class ForgotPasswordData(
    @SerializedName("jwt") var jwt : String,
    @SerializedName("code") var code : String
): Parcelable