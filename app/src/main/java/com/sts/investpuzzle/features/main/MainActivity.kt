package com.sts.investpuzzle.features.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.sts.investpuzzle.R
import com.sts.investpuzzle.base.BaseActivity
import com.sts.investpuzzle.core.navigation.INavigationService
import com.sts.investpuzzle.databinding.ActivityMainBinding
import com.sts.investpuzzle.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>(){

    @Inject
    lateinit var navigationService: INavigationService

    override val progressView: View get() = viewBind.mProgressView.root
    override val errorView: View get() = viewBind.mErrorView.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewBinding(ActivityMainBinding.inflate(layoutInflater))

        withViewModel<MainActivityViewModel> {}

        setUp()
    }

    override fun setUp() {
        navigationService.openStartupScreen()
    }

    fun setUpHomeScreen(){
        navigationService.openStockScreen()
        viewBind.bottomNav.visibility = View.VISIBLE
        viewBind.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId){
                R.id.menuStocks -> navigationService.openStockScreen()
                R.id.menuNews -> navigationService.openNewsScreen()
                R.id.menuRanking -> navigationService.openRankingScreen()
                R.id.menuDailyMovers -> navigationService.openMoverScreen()
                R.id.menuProfile -> navigationService.openMoverScreen()
            }
            true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        for (fragment in supportFragmentManager.fragments){
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigationService.attachToActivity(this)
    }

    override fun onClick(v: View?) {}
}