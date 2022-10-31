package com.sts.investpuzzle.features.news

import com.sts.investpuzzle.base.BaseViewModel
import com.sts.investpuzzle.utils.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class NewsScreenViewModel @Inject constructor(
    val schedulerProvider: SchedulerProvider,
    val compositeDisposable: CompositeDisposable,
    newsScreenInteractor: NewsScreenInteractor
) : BaseViewModel<NewsScreenInteractor>(schedulerProvider, compositeDisposable, newsScreenInteractor){

}