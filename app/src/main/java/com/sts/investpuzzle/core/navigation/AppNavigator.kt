package com.sts.investpuzzle.core.navigation

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.sts.investpuzzle.R

class AppNavigator (activity: AppCompatActivity, @IdRes container : Int) : AppNavigator(activity, container){
    override fun setupFragmentTransaction(
        screen: FragmentScreen,
        fragmentTransaction: FragmentTransaction,
        currentFragment: Fragment?,
        nextFragment: Fragment
    ) {

        currentFragment?.let {
            fragmentTransaction.setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
        }

        super.setupFragmentTransaction(screen, fragmentTransaction, currentFragment, nextFragment)
    }
}