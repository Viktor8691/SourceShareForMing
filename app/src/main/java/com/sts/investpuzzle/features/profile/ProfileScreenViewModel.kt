package com.sts.investpuzzle.features.profile

import com.sts.investpuzzle.base.BaseViewModel
import com.sts.investpuzzle.utils.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    val schedulerProvider: SchedulerProvider,
    val compositeDisposable: CompositeDisposable,
    profileScreenInteractor: ProfileScreenInteractor
) : BaseViewModel<ProfileScreenInteractor>(schedulerProvider, compositeDisposable, profileScreenInteractor){

}