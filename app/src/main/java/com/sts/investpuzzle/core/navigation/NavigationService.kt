package com.sts.investpuzzle.core.navigation

import android.content.Context
import android.os.Bundle
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import com.sts.investpuzzle.features.main.MainActivity
import com.sts.investpuzzle.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NavigationService(cicerone: Cicerone<Router>) : INavigationService {
    private val router = cicerone.router
    private val navigationHolder = cicerone.getNavigatorHolder()

    override fun attachToActivity(context: Context) {
        context as MainActivity
        navigationHolder.setNavigator(AppNavigator(context, R.id.frmContainer))
    }

    override fun openStartupScreen() {
        newRootScreen(Screens.getStartupScreen())
    }

    override fun openSignInScreen() {
        navigateTo(Screens.getSignInScreen())
    }

    override fun openSignupScreen() {
        navigateTo(Screens.getSignupScreen())
    }

    override fun openInputOTPScreen(bundle: Bundle) {
        navigateTo(Screens.GetInputOTPScreen(bundle))
    }

    override fun openForgotPwdScreen() {
        navigateTo(Screens.getForgotPwdScreen())
    }

    override fun openInputUserInfoScreen(bundle: Bundle) {
        navigateTo(Screens.GetInputUserInfoScreen(bundle))
    }

    override fun openWelcomeSignupScreen() {
        newRootScreen(Screens.getWelcomeSignupScreen())
    }

    override fun openStockScreen() {
        replaceScreen(Screens.getStockScreen())
    }

    override fun openNewsScreen() {
        replaceScreen(Screens.getNewsScreen())
    }

    override fun openRankingScreen() {
        replaceScreen(Screens.getRankingScreen())
    }

    override fun openMoverScreen() {
        replaceScreen(Screens.getMoverScreen())
    }

    override fun openProfileScreen() {
        replaceScreen(Screens.getProfileScreen())
    }

    override fun openResetScreen(bundle: Bundle) {
        navigateTo(Screens.GetResetPasswordScreen(bundle))
    }

    override fun openResetPwdSuccessScreen() {
        newRootScreen(Screens.getResetPwdSuccessScreen())
    }

    override fun goBack() {
        back()
    }

    private fun replaceScreen(screen: Screen){
        router.replaceScreen(screen)
        /*CoroutineScope(Dispatchers.Main).launch {
            router.replaceScreen(screen)
        }*/
    }

    private fun newRootScreen(screen: Screen){
        router.newRootScreen(screen)
        /*CoroutineScope(Dispatchers.Main).launch {
            router.newRootScreen(screen)
        }*/
    }

    private fun navigateTo(screen: Screen){
        router.navigateTo(screen)
        /*CoroutineScope(Dispatchers.Main).launch {
            router.navigateTo(screen)
        }*/
    }

    private fun back(){
        router.exit()
        /*CoroutineScope(Dispatchers.Main).launch {
            router.exit()
        }*/
    }
}