package com.sts.investpuzzle.features.user_management.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sts.investpuzzle.R
import com.sts.investpuzzle.base.BaseFragment
import com.sts.investpuzzle.base.BaseViewModel
import com.sts.investpuzzle.constants.SignInType
import com.sts.investpuzzle.databinding.ScreenSigninBinding
import com.sts.investpuzzle.extensions.observeEvent
import com.sts.investpuzzle.features.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInScreen : BaseFragment<SignInScreenViewModel, ScreenSigninBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        setupViewBinding(ScreenSigninBinding.inflate(inflater, container, false))

        withViewModel<SignInScreenViewModel> {
            observeEvent(signInSuccess) {
                if (it) gotoStockScreen()
            }
        }

        return viewBind.root
    }

    private fun gotoStockScreen(){
        navigationService.goBack()
        viewModel.signInStatus = true
        (activity as MainActivity).setUpHomeScreen()
    }

    override fun setUp() {
        viewBind.backButton.setOnClickListener(this)
        viewBind.txvForgotPwd.setOnClickListener(this)
        viewBind.txvSignIn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.backButton -> {
                navigationService.goBack()
            }
            R.id.txvForgotPwd -> {
                navigationService.openForgotPwdScreen()
            }
            R.id.txvSignIn -> {
                if (viewBind.edtEmail.text.toString().isEmpty()) {
                    showError(getString(R.string.error), getString(R.string.input_email))
                }else if (viewBind.edtPassword.text.toString().isEmpty()){
                    showError(getString(R.string.error), getString(R.string.input_password))
                }else {
                    viewModel.signIn(
                        viewBind.edtEmail.text.toString(),
                        viewBind.edtPassword.text.toString(),
                        SignInType.EMAIL, "",
                        viewBind.chbRememberMe.isChecked
                    )
                }
            }
        }
    }
}