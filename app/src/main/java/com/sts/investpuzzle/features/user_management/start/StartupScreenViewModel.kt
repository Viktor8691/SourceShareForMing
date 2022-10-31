package com.sts.investpuzzle.features.user_management.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sts.investpuzzle.base.BaseViewModel
import com.sts.investpuzzle.core.data.session.SessionHelper
import com.sts.investpuzzle.utils.Event
import com.sts.investpuzzle.utils.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class StartupScreenViewModel @Inject constructor(
    val schedulerProvider: SchedulerProvider,
    val compositeDisposable: CompositeDisposable,
    interactor: StartupScreenInteractor,
    private val sessionHelper: SessionHelper
) : BaseViewModel<StartupScreenInteractor>(schedulerProvider, compositeDisposable, interactor){

    private val _isAccessoryFetched = MutableLiveData<Event<Boolean>>()
    val isAccessoryFetched : LiveData<Event<Boolean>> get() = _isAccessoryFetched

    init {
        signInStatus = sessionHelper.getSignInRequest() != null
        getAccessories()
    }

    fun getAccessories(){
        showLoading()
        compositeDisposable.add(interactor.getAccessories()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({
                hideLoading()
                accessoryData = it.data
                _isAccessoryFetched.value = Event(true)
            }, {
                hideLoading()
            }))
    }
}