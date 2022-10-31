package com.sts.investpuzzle.features.ranking

import com.sts.investpuzzle.base.BaseViewModel
import com.sts.investpuzzle.utils.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class RankingScreenViewModel @Inject constructor(
    val schedulerProvider: SchedulerProvider,
    val compositeDisposable: CompositeDisposable,
    rankingScreenInteractor: RankingScreenInteractor
)  : BaseViewModel<RankingScreenInteractor>(schedulerProvider, compositeDisposable, rankingScreenInteractor){

}