package com.sts.investpuzzle.features.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sts.investpuzzle.base.BaseFragment
import com.sts.investpuzzle.databinding.ScreenNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsScreen : BaseFragment<NewsScreenViewModel, ScreenNewsBinding>(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        setupViewBinding(ScreenNewsBinding.inflate(inflater, container, false))

        withViewModel<NewsScreenViewModel> {  }
        return viewBind.root
    }

    override fun setUp() {

    }
}