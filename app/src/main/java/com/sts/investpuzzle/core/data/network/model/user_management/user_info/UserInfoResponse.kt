package com.sts.investpuzzle.core.data.network.model.user_management.user_info

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sts.investpuzzle.core.data.network.model.accessories.Country
import com.sts.investpuzzle.core.data.network.model.accessories.Education
import com.sts.investpuzzle.core.data.network.model.accessories.Pronouns
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfoResponse (
    @SerializedName("message") val message : String,
    @SerializedName("data") val data : UserInfo,
) : Parcelable

@Parcelize
data class UserInfo(
    @SerializedName("profile") val profile : Profile,
) : Parcelable

@Parcelize
data class Profile (
    @SerializedName("id") val id : String,
    @SerializedName("username") val username : String,
    @SerializedName("email") val email : String,
    @SerializedName("pronounsId") val pronounsId : String,
    @SerializedName("yearOfBirth") val yearOfBirth : String,
    @SerializedName("educationId") val educationId : String,
    @SerializedName("otherEducation") val otherEducation : String,
    @SerializedName("otherPronouns") val otherPronouns : String,
    @SerializedName("countryId") val countryId : String,
    @SerializedName("socialId") val socialId : String,
    @SerializedName("loginType") val loginType : String,
    @SerializedName("emailVerified") val emailVerified : Boolean,
    @SerializedName("availableStock") val availableStock : Long,
    @SerializedName("token") val token : String,
    @SerializedName("pronouns") val pronouns : Pronouns,
    @SerializedName("country") val country : Country,
    @SerializedName("education") val education : Education,
    @SerializedName("followers") val followers : Int,
    @SerializedName("following") val following : Int,
    @SerializedName("bio") val bio : String?,
) : Parcelable