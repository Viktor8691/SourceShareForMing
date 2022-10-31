package com.sts.investpuzzle.features.user_management.input_otp

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.sts.investpuzzle.base.BaseViewModel
import com.sts.investpuzzle.constants.CAN_NOT_SIGNUP_MESSAGE
import com.sts.investpuzzle.constants.CAN_NOT_SIGNUP_TITLE
import com.sts.investpuzzle.core.data.network.model.user_management.forgot_password.ForgotPasswordResponse
import com.sts.investpuzzle.core.data.network.model.user_management.signup.SignupRequest
import com.sts.investpuzzle.core.data.network.model.user_management.signup.SignupResponse
import com.sts.investpuzzle.core.data.prefs.PreferencesHelper
import com.sts.investpuzzle.utils.Event
import com.sts.investpuzzle.utils.Utils
import com.sts.investpuzzle.utils.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable

import javax.inject.Inject

@HiltViewModel
class InputOTPScreenViewModel  @Inject constructor(
    val schedulerProvider: SchedulerProvider,
    val compositeDisposable: CompositeDisposable,
    inputOTPScreenInteractor: InputOTPScreenInteractor,
    val preferencesHelper: PreferencesHelper,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<InputOTPScreenInteractor>(schedulerProvider, compositeDisposable, inputOTPScreenInteractor){

    val signupResponse by lazy {
        savedStateHandle.get<SignupResponse>(InputOTPScreen.SIGNUP_ARG)
    }

    val forgotPasswordResponse by lazy {
        savedStateHandle.get<ForgotPasswordResponse>(InputOTPScreen.FORGOT_PASSWORD_ARG)
    }

    val handler5Min = Handler(Looper.getMainLooper())
    private val timer = object : Runnable {
        override fun run() {
            min_5--
            _timer_5min.postValue(Event(Utils.intToStringTime(min_5)))
            handler5Min.postDelayed(this, 1000)
        }
    }
    private var min_5 = 300

    private val _timer_5min = MutableLiveData<Event<String>>()
    val timer_5_min : LiveData<Event<String>> get() = _timer_5min

    init {
        handler5Min.post(timer)
    }

    fun resendCode(){
        if (signupResponse != null){
            if (!canTrySignup()) return
            callInteractor(interactor.resendSignupCode(SignupRequest(signupResponse!!.email, signupResponse!!.username))) {
                min_5 = 300
                signupResponse!!.code = it.code
            }
        }else if (forgotPasswordResponse != null){
            callInteractor(interactor.resendForgotPasswordCode(forgotPasswordResponse!!.email)) {
                min_5 = 300
                forgotPasswordResponse!!.data.code = it.data.code
            }
        }
    }

    fun stopTimer(){
        handler5Min.removeCallbacks(timer)
    }

    override fun onCleared() {
        super.onCleared()
        handler5Min.removeCallbacks(timer)
    }

    private fun canTrySignup() : Boolean{
        val diff = preferencesHelper.lastSignupTryTime / (1000 * 60 * 60) - System.currentTimeMillis() / (1000 * 60 * 60)
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