package com.sts.investpuzzle.features.stocks

import com.sts.investpuzzle.base.BaseViewModel
import com.sts.investpuzzle.utils.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class StockScreenViewModel @Inject constructor(
    val schedulerProvider: SchedulerProvider,
    val compositeDisposable: CompositeDisposable,
    val stockScreenInteractor: StockScreenInteractor
) : BaseViewModel<StockScreenInteractor>(schedulerProvider, compositeDisposable, stockScreenInteractor){

}