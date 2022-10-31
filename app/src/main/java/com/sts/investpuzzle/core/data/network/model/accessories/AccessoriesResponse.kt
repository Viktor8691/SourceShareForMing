package com.sts.investpuzzle.core.data.network.model.accessories

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AccessoriesResponse (
    @SerializedName("data") val data : AccessoryData,
    @SerializedName("message") val message : String
) : Parcelable

@Parcelize
data class AccessoryData (
    @SerializedName("pronounses") val pronounses : List<Pronouns> = emptyList(),
    @SerializedName("educations") val educations : List<Education> = emptyList(),
    @SerializedName("countries") val countries : List<Country> = emptyList()
) : Parcelable

@Parcelize
data class Pronouns (
    @SerializedName("title") val title: String,
    @SerializedName("id") val id: String = ""
) : Parcelable

@Parcelize
data class Education (
    @SerializedName("title") val title: String,
    @SerializedName("id") val id: String,
): Parcelable

@Parcelize
data class Country (
    @SerializedName("name") val name: String,
    @SerializedName("code") val code: String,
    @SerializedName("capital") val capital: String?,
    @SerializedName("region") val region: String?,
    @SerializedName("currency") val currency: Currency,
    @SerializedName("language") val language: Language?,
    @SerializedName("flag") val flag: String?,
    @SerializedName("id") val id: String
): Parcelable

@Parcelize
data class Currency (
    @SerializedName("code") val code: String,
    @SerializedName("name") val name: String,
    @SerializedName("symbol") val symbol: String
): Parcelable

@Parcelize
data class Language (
    @SerializedName("code") val code: String,
    @SerializedName("name") val name: String
): Parcelable

