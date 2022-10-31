package com.sts.investpuzzle.features.stocks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sts.investpuzzle.base.BaseFragment
import com.sts.investpuzzle.databinding.ScreenStocksBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StockScreen : BaseFragment<StockScreenViewModel, ScreenStocksBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        setupViewBinding(ScreenStocksBinding.inflate(inflater, container, false))

        withViewModel<StockScreenViewModel> {  }

        return viewBind.root
    }

    override fun setUp() {

    }
}