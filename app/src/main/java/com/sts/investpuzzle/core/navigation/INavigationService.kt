package com.sts.investpuzzle.core.navigation

import android.content.Context
import android.os.Bundle

interface INavigationService {
    fun attachToActivity(context: Context)
    fun openStartupScreen()
    fun openSignInScreen()
    fun openSignupScreen()
    fun openInputOTPScreen(bundle: Bundle)
    fun openForgotPwdScreen()
    fun openInputUserInfoScreen(bundle: Bundle)
    fun openWelcomeSignupScreen()
    fun openStockScreen()
    fun openNewsScreen()
    fun openRankingScreen()
    fun openMoverScreen()
    fun openProfileScreen()
    fun openResetScreen(bundle: Bundle)
    fun openResetPwdSuccessScreen()
    fun goBack()
}