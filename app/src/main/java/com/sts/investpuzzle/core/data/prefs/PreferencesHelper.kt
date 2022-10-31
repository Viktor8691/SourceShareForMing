package com.sts.investpuzzle.core.data.prefs

import com.sts.investpuzzle.core.data.network.model.user_management.signin.SignInRequest
import javax.inject.Singleton

@Singleton
interface PreferencesHelper {
    var signupTryCount : Int
    var lastSignupTryTime : Long
    fun deleteSessionPreference()
    fun saveSignInRequest(signInRequest: SignInRequest)
    fun getSignInRequest() : SignInRequest?
}