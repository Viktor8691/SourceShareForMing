package com.sts.investpuzzle.features.user_management.forgot_password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sts.investpuzzle.base.BaseInteractor
import com.sts.investpuzzle.base.BaseViewModel
import com.sts.investpuzzle.core.data.network.model.user_management.forgot_password.ForgotPasswordResponse
import com.sts.investpuzzle.core.data.session.SessionHelper
import com.sts.investpuzzle.utils.Event
import com.sts.investpuzzle.utils.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class ForgotPwdScreenViewModel  @Inject constructor(
    val schedulerProvider: SchedulerProvider,
    val compositeDisposable: CompositeDisposable,
    forgotPwdScreenInteractor: ForgotPwdScreenInteractor,
    private val sessionHelper: SessionHelper
) : BaseViewModel<ForgotPwdScreenInteractor>(schedulerProvider, compositeDisposable, forgotPwdScreenInteractor) {

    private val _forgotPasswordResponse = MutableLiveData<Event<ForgotPasswordResponse>>()
    val forgotPasswordResponse : LiveData<Event<ForgotPasswordResponse>> get() = _forgotPasswordResponse

    fun forgotPassword(email : String) {
        callInteractor(interactor.forgotPassword(email)){
            sessionHelper.setAuthToken(it.data.jwt)
            _forgotPasswordResponse.value = Event(it)
        }
    }
}