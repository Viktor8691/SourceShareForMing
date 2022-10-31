package com.sts.investpuzzle.features.user_management.reset_password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.sts.investpuzzle.base.BaseViewModel
import com.sts.investpuzzle.core.data.network.model.user_management.forgot_password.ForgotPasswordResponse
import com.sts.investpuzzle.core.data.network.model.user_management.reset_password.ResetPasswordRequest
import com.sts.investpuzzle.core.data.network.model.user_management.signin.SignInResponse
import com.sts.investpuzzle.core.data.session.SessionHelper
import com.sts.investpuzzle.utils.Event
import com.sts.investpuzzle.utils.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class ResetPasswordScreenViewModel @Inject constructor(
    val schedulerProvider: SchedulerProvider,
    val compositeDisposable: CompositeDisposable,
    resetPasswordScreenInteractor: ResetPasswordScreenInteractor,
    savedStateHandle: SavedStateHandle,
    private val sessionHelper: SessionHelper
)  : BaseViewModel<ResetPasswordScreenInteractor>(schedulerProvider, compositeDisposable, resetPasswordScreenInteractor){

    val forgotPasswordResponse by lazy {
        savedStateHandle.get<ForgotPasswordResponse>(ResetPasswordScreen.RESET_SCREEN_ARG)
    }

    private val _signInRequest = MutableLiveData<Event<SignInResponse>>()
    val signInRequest : LiveData<Event<SignInResponse>> get() = _signInRequest

    fun resetPassword(password : String){
        val resetPasswordRequest = ResetPasswordRequest(
            email = forgotPasswordResponse!!.email,
            password = password,
            code = forgotPasswordResponse!!.data.code,
        )

        callInteractor(interactor.resetPassword(resetPasswordRequest)){
            sessionHelper.setAuthToken(it.data.user.token)
            userDetail = it.data.user
            _signInRequest.value = Event(it)
        }
    }
}