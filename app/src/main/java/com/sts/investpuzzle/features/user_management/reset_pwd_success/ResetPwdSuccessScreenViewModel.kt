package com.sts.investpuzzle.features.user_management.reset_pwd_success

import com.sts.investpuzzle.base.BaseViewModel
import com.sts.investpuzzle.utils.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class ResetPwdSuccessScreenViewModel @Inject constructor(
    val schedulerProvider: SchedulerProvider,
    val compositeDisposable: CompositeDisposable,
    resetPwdSuccessScreenInteractor: ResetPwdSuccessScreenInteractor
) : BaseViewModel<ResetPwdSuccessScreenInteractor>(schedulerProvider, compositeDisposable, resetPwdSuccessScreenInteractor){

}