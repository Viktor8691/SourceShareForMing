package com.sts.investpuzzle.core.data.network

import com.sts.investpuzzle.core.data.prefs.PreferencesHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiEndPoint @Inject constructor(private val preferencesHelper: PreferencesHelper) {
    private val BASE_URL = "https://wafflestock.com/"
    val signInEndPoint : String get() = BASE_URL + "auth/login"
    val authenticateUser : String get() = BASE_URL + "auth/authenticateUser"
    val getAccessories : String get() = BASE_URL + "common/getaccessories"
    val registerUser : String get() = BASE_URL + "auth/registerUser"
    val updateProfile : String get() = BASE_URL + "users/profile"
    val forgotPassword : String get() = BASE_URL + "auth/forgotPassowrdEmailCheck"
    val resetPassword : String get() = BASE_URL + "auth/resetPassword"
}