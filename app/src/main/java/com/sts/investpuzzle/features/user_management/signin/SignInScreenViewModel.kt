package com.sts.investpuzzle.features.user_management.signin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sts.investpuzzle.base.BaseViewModel
import com.sts.investpuzzle.constants.SignInType
import com.sts.investpuzzle.core.data.network.model.user_management.signin.SignInRequest
import com.sts.investpuzzle.core.data.session.SessionHelper
import com.sts.investpuzzle.utils.Event
import com.sts.investpuzzle.utils.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class SignInScreenViewModel @Inject constructor(
        val schedulerProvider: SchedulerProvider,
        val compositeDisposable: CompositeDisposable,
        signInScreenInteractor: SignInScreenInteractor,
        private val sessionHelper: SessionHelper
) : BaseViewModel<SignInScreenInteractor>(schedulerProvider, compositeDisposable, signInScreenInteractor) {

    private val _signInSuccess = MutableLiveData<Event<Boolean>>()
    val signInSuccess : LiveData<Event<Boolean>> get() = _signInSuccess

    fun signIn(email : String, password : String, signInType : SignInType, socialId : String, rememberMe : Boolean){

        val signInRequest = SignInRequest(email, password, signInType.ordinal, socialId)
        callInteractor(interactor.doSignIn(signInRequest)) {
            if (rememberMe) sessionHelper.saveSignInRequest(signInRequest)
            userDetail = it.data.user
            _signInSuccess.value = Event(true)
        }
    }
}