package com.sts.investpuzzle.features.user_management.welcome_signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sts.investpuzzle.base.BaseViewModel
import com.sts.investpuzzle.core.data.network.model.user_management.update_profile.UpdateProfileRequest
import com.sts.investpuzzle.utils.Event
import com.sts.investpuzzle.utils.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class WelcomeSignupViewModel @Inject constructor(
    val schedulerProvider: SchedulerProvider,
    val compositeDisposable: CompositeDisposable,
    welcomeSignupInteractor: WelcomeSignupInteractor
) : BaseViewModel<WelcomeSignupInteractor>(schedulerProvider, compositeDisposable, welcomeSignupInteractor){

    private val _bioUpdateSuccess = MutableLiveData<Event<Boolean>>()
    val bioUpdateSuccess : LiveData<Event<Boolean>> get() = _bioUpdateSuccess


    fun saveBio(bio : String) {
        val updateProfileRequest = UpdateProfileRequest(bio = bio)
        callInteractor(interactor.updateProfile(updateProfileRequest)) {
            _bioUpdateSuccess.value = Event(true)
        }
    }
}