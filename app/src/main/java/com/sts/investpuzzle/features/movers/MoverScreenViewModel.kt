package com.sts.investpuzzle.features.movers

import com.sts.investpuzzle.base.BaseViewModel
import com.sts.investpuzzle.utils.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class MoverScreenViewModel @Inject constructor(
    val schedulerProvider: SchedulerProvider,
    val compositeDisposable: CompositeDisposable,
    moverScreenInteractor: MoverScreenInteractor
) : BaseViewModel<MoverScreenInteractor>(schedulerProvider, compositeDisposable, moverScreenInteractor){

}