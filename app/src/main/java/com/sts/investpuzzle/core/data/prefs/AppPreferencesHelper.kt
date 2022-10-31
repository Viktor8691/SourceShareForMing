package com.sts.investpuzzle.core.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.sts.investpuzzle.core.data.network.model.user_management.signin.SignInRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppPreferencesHelper @Inject constructor(@ApplicationContext val context: Context) : PreferencesHelper{

    private val PREF_NAME = "WaffleStock"
    private val SIGNUP_TRY_COUNT = "SIGNUP_TRY_COUNT"
    private val LAST_SIGNUP_TRY_TIME = "LAST_SIGNUP_TRY_TIME"
    private val SIGIN_REQUEST = "SIGNIN_REQUEST"

    private val mPrefs : SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    override var signupTryCount: Int
        get() = mPrefs.getInt(SIGNUP_TRY_COUNT, 0)
        set(value) = mPrefs.edit().putInt(SIGNUP_TRY_COUNT, value).apply()
    override var lastSignupTryTime: Long
        get() = mPrefs.getLong(LAST_SIGNUP_TRY_TIME, 0)
        set(value) = mPrefs.edit().putLong(LAST_SIGNUP_TRY_TIME, value).apply()

    override fun deleteSessionPreference() {}
    override fun saveSignInRequest(signInRequest: SignInRequest) {
        val json = Gson().toJson(signInRequest)
        mPrefs.edit().putString(SIGIN_REQUEST, json).apply()
    }

    override fun getSignInRequest(): SignInRequest? {
        return mPrefs.getString(SIGIN_REQUEST, "")?.let {
            if (it.isEmpty())
                null
            else {
                try {
                    Gson().fromJson(it, SignInRequest::class.java)
                } catch (e : Exception) {
                    null
                }
            }
        }
    }
}