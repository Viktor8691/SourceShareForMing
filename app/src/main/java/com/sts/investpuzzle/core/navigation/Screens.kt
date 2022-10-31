package com.sts.investpuzzle.core.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.sts.investpuzzle.features.movers.MoverScreen
import com.sts.investpuzzle.features.news.NewsScreen
import com.sts.investpuzzle.features.profile.ProfileScreen
import com.sts.investpuzzle.features.profile.ProfileScreenViewModel
import com.sts.investpuzzle.features.ranking.RankingScreen
import com.sts.investpuzzle.features.stocks.StockScreen
import com.sts.investpuzzle.features.user_management.forgot_password.ForgotPwdScreen
import com.sts.investpuzzle.features.user_management.input_otp.InputOTPScreen
import com.sts.investpuzzle.features.user_management.input_user_detail.InputUserInfoScreen
import com.sts.investpuzzle.features.user_management.reset_password.ResetPasswordScreen
import com.sts.investpuzzle.features.user_management.reset_pwd_success.ResetPwdSuccessScreen
import com.sts.investpuzzle.features.user_management.signin.SignInScreen
import com.sts.investpuzzle.features.user_management.signup.SignupScreen
import com.sts.investpuzzle.features.user_management.start.StartupScreen
import com.sts.investpuzzle.features.user_management.welcome_signup.WelcomeSignupScreen

object Screens {
    fun getStartupScreen() = FragmentScreen{StartupScreen()}
    fun getSignInScreen() = FragmentScreen{SignInScreen()}
    fun getSignupScreen() = FragmentScreen{SignupScreen()}
    class GetInputOTPScreen (private val bundle : Bundle) : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment {
            val screen = InputOTPScreen()
            screen.arguments = bundle
            return screen
        }
    }
    fun getForgotPwdScreen() = FragmentScreen{ForgotPwdScreen()}
    class GetInputUserInfoScreen (private val bundle: Bundle) : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment {
            val screen = InputUserInfoScreen()
            screen.arguments = bundle
            return screen
        }
    }
    fun getWelcomeSignupScreen() = FragmentScreen{WelcomeSignupScreen()}
    fun getStockScreen() = FragmentScreen{StockScreen()}
    fun getNewsScreen() = FragmentScreen{NewsScreen()}
    fun getRankingScreen() = FragmentScreen{RankingScreen()}
    fun getMoverScreen() = FragmentScreen{MoverScreen()}
    fun getProfileScreen() = FragmentScreen{ ProfileScreen() }
    class GetResetPasswordScreen(private val bundle: Bundle) : FragmentScreen {
        override fun createFragment(factory: FragmentFactory): Fragment {
            val screen = ResetPasswordScreen()
            screen.arguments = bundle
            return screen
        }
    }
    fun getResetPwdSuccessScreen() = FragmentScreen{ResetPwdSuccessScreen()}
}