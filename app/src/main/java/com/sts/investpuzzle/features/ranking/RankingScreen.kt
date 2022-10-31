package com.sts.investpuzzle.features.ranking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sts.investpuzzle.base.BaseFragment
import com.sts.investpuzzle.databinding.ScreenRankingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankingScreen : BaseFragment<RankingScreenViewModel, ScreenRankingBinding> (){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setupViewBinding(ScreenRankingBinding.inflate(inflater, container, false))
        withViewModel<RankingScreenViewModel> {  }
        return viewBind.root
    }

    override fun setUp() {

    }
}