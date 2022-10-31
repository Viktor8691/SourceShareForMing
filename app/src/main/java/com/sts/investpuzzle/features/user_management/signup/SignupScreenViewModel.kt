package com.sts.investpuzzle.features.user_management.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sts.investpuzzle.base.BaseViewModel
import com.sts.investpuzzle.constants.CAN_NOT_SIGNUP_MESSAGE
import com.sts.investpuzzle.constants.CAN_NOT_SIGNUP_TITLE
import com.sts.investpuzzle.core.data.network.model.user_management.signin.SignInRequest
import com.sts.investpuzzle.core.data.network.model.user_management.signup.SignupRequest
import com.sts.investpuzzle.core.data.network.model.user_management.signup.SignupResponse
import com.sts.investpuzzle.core.data.prefs.PreferencesHelper
import com.sts.investpuzzle.core.data.session.SessionHelper
import com.sts.investpuzzle.utils.Event
import com.sts.investpuzzle.utils.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class SignupScreenViewModel @Inject constructor(
    val schedulerProvider: SchedulerProvider,
    val compositeDisposable: CompositeDisposable,
    signupScreenInteractor: SignupScreenInteractor,
    val preferencesHelper: PreferencesHelper,
    val sessionHelper: SessionHelper
) : BaseViewModel<SignupScreenInteractor>(schedulerProvider, compositeDisposable, signupScreenInteractor) {

    private val _signupResponse = MutableLiveData<Event<SignupResponse>>()
    val signupResponse : LiveData<Event<SignupResponse>> get() = _signupResponse

    fun signup(email : String, username : String, password : String) {

        if (!canTrySignup()) return

        callInteractor(interactor.signup(SignupRequest(email, username))) {
            it.password = password
            it.email = email
            it.username = username
            sessionHelper.setAuthToken(it.data.jwt)
            _signupResponse.value = Event(it)
        }
    }

    private fun canTrySignup() : Boolean{
        val diff = System.currentTimeMillis() / (1000 * 60 * 60) - preferencesHelper.lastSignupTryTime / (1000 * 60 * 60)
        if (diff > 2) {
            preferencesHelper.signupTryCount = 0
        }

        preferencesHelper.signupTryCount++
        if (preferencesHelper.signupTryCount > 5) {
            preferencesHelper.lastSignupTryTime = System.currentTimeMillis()
            showError(CAN_NOT_SIGNUP_TITLE, CAN_NOT_SIGNUP_MESSAGE)
            return false
        }

        return true
    }
}