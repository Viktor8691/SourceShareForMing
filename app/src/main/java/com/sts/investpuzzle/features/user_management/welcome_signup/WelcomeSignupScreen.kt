package com.sts.investpuzzle.features.user_management.welcome_signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sts.investpuzzle.R
import com.sts.investpuzzle.base.BaseFragment
import com.sts.investpuzzle.databinding.DialogProfileHeadlineBinding
import com.sts.investpuzzle.databinding.ScreenWelcomeSignupBinding
import com.sts.investpuzzle.extensions.observeEvent
import com.sts.investpuzzle.features.main.MainActivity
import com.sts.investpuzzle.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeSignupScreen : BaseFragment<WelcomeSignupViewModel, ScreenWelcomeSignupBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        setupViewBinding(ScreenWelcomeSignupBinding.inflate(inflater, container, false))
        withViewModel<WelcomeSignupViewModel> {
            observeEvent(bioUpdateSuccess) {
                if (it) gotoStockScreen()
            }
        }
        return viewBind.root
    }

    override fun setUp() {
        viewBind.txvGetStarted.setOnClickListener(this)
    }

    private fun gotoStockScreen(){
        viewModel.signInStatus = true
        (activity as MainActivity).setUpHomeScreen()
    }

    private fun showProfileHeadlineDialog(){
        val dialogViewBind = DialogProfileHeadlineBinding.inflate(LayoutInflater.from(context), viewBind.root, false)
        val dialog = Utils.customDialog(requireContext(), dialogViewBind)

        dialogViewBind.txvSave.setOnClickListener {
            viewModel.saveBio(dialogViewBind.edtBio.text.toString())
            dialog.dismiss()
        }

        dialogViewBind.txvSkip.setOnClickListener {
            gotoStockScreen()
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.txvGetStarted -> {
                showProfileHeadlineDialog()
            }
        }
    }
}