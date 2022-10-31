package com.sts.investpuzzle.features.user_management.input_user_detail

import android.content.Context
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.sts.investpuzzle.base.BaseViewModel
import com.sts.investpuzzle.constants.EDUCATION_ERROR_MESSAGE
import com.sts.investpuzzle.constants.EDUCATION_ERROR_TITLE
import com.sts.investpuzzle.constants.GENDER_ERROR_MESSAGE
import com.sts.investpuzzle.constants.GENDER_ERROR_TITLE
import com.sts.investpuzzle.core.data.network.model.accessories.Country
import com.sts.investpuzzle.core.data.network.model.user_management.signin.SignInRequest
import com.sts.investpuzzle.core.data.network.model.user_management.signup.SignupResponse
import com.sts.investpuzzle.core.data.network.model.user_management.user_info.UserInfoRequest
import com.sts.investpuzzle.core.data.session.SessionHelper
import com.sts.investpuzzle.utils.Event
import com.sts.investpuzzle.utils.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class InputUserInfoScreenViewModel @Inject constructor(
    val schedulerProvider: SchedulerProvider,
    val compositeDisposable: CompositeDisposable,
    inputUserInfoScreenInteractor: InputUserInfoScreenInteractor,
    savedStateHandle: SavedStateHandle,
    val sessionHelper: SessionHelper,
    @ApplicationContext private val context: Context
) : BaseViewModel<InputUserInfoScreenInteractor>(schedulerProvider, compositeDisposable, inputUserInfoScreenInteractor){

    val _signupResponse by lazy {
        savedStateHandle.get<SignupResponse>(InputUserInfoScreen.SCREEN_ARG)
    }

    private val _localCountry = MutableLiveData<Event<Country>>()
    val localCountry : LiveData<Event<Country>> get() = _localCountry

    private val _isRegisterSuccess = MutableLiveData<Event<Boolean>>()
    val isRegisterSuccess : LiveData<Event<Boolean>> get() = _isRegisterSuccess


    val userInfoRequest = UserInfoRequest()
    init {
        _signupResponse?.let {
            userInfoRequest.password = it.password
        }

        val localCode= context.resources.configuration.locale.country
        accessoryData.countries.find { it.code.equals(localCode, ignoreCase = true) }?.let {
            _localCountry.value = Event(it)
            userInfoRequest.countryId = it.id
        }
    }

    fun uploadUserInfo(){
        if (userInfoRequest.pronounsId.isEmpty() && userInfoRequest.otherPronouns.isEmpty()){
            showError(GENDER_ERROR_TITLE, GENDER_ERROR_MESSAGE)
            return
        }
        if (userInfoRequest.educationId.isEmpty() && userInfoRequest.otherEducation.isEmpty()){
            showError(EDUCATION_ERROR_TITLE, EDUCATION_ERROR_MESSAGE)
            return
        }

        callInteractor(interactor.uploadUserInfo(userInfoRequest)) {
            sessionHelper.setAuthToken(it.data.profile.token)
            userDetail = it.data.profile
            sessionHelper.saveSignInRequest(
                SignInRequest(
                email = _signupResponse!!.email,
                password = _signupResponse!!.password,
            )
            )
            _isRegisterSuccess.value = Event(true)
        }
    }
}