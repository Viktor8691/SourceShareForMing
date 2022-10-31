package com.sts.investpuzzle.features.main

import com.sts.investpuzzle.base.BaseViewModel
import com.sts.investpuzzle.core.data.session.SessionHelper
import com.sts.investpuzzle.utils.Event
import com.sts.investpuzzle.utils.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    val schedulerProvider: SchedulerProvider,
    val compositeDisposable: CompositeDisposable,
    mainActivityInteractor: MainActivityInteractor,
) : BaseViewModel<MainActivityInteractor> (schedulerProvider, compositeDisposable, mainActivityInteractor){

}