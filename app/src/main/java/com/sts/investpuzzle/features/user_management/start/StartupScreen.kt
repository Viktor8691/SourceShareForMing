package com.sts.investpuzzle.features.user_management.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sts.investpuzzle.R
import com.sts.investpuzzle.base.BaseFragment
import com.sts.investpuzzle.databinding.ScreenStartupBinding
import com.sts.investpuzzle.extensions.observeEvent
import com.sts.investpuzzle.features.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartupScreen : BaseFragment<StartupScreenViewModel, ScreenStartupBinding>(){

    private var nextScreen : Int = 0 // 1 = SignIn, 2 = Signup

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        setupViewBinding(ScreenStartupBinding.inflate(inflater, container, false))

        withViewModel<StartupScreenViewModel> {
            observeEvent(isAccessoryFetched){
                if (it) checkSigInStatus()
            }
        }

        return viewBind.root
    }

    private fun checkSigInStatus(){
        if (viewModel.signInStatus){
            (activity as MainActivity).setUpHomeScreen()
        }else {
            when(nextScreen) {
                1 -> navigationService.openSignInScreen()
                2 -> navigationService.openSignupScreen()
            }
        }
    }

    override fun setUp() {
        viewBind.txvSignIn.setOnClickListener(this)
        viewBind.txvSignUp.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.txvSignIn -> {
                if (viewModel.isAccessoryFetched.value?.peekContent() == true){
                    navigationService.openSignInScreen()
                }else {
                    nextScreen = 1
                    viewModel.getAccessories()
                }
            }
            R.id.txvSignUp -> {
                if (viewModel.isAccessoryFetched.value?.peekContent() == true) {
                    navigationService.openSignupScreen()
                }else {
                    nextScreen = 2
                    viewModel.getAccessories()
                }
            }
        }
    }

}