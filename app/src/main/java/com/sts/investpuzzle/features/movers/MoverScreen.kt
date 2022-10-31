package com.sts.investpuzzle.features.movers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sts.investpuzzle.base.BaseFragment
import com.sts.investpuzzle.databinding.ScreenMoversBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoverScreen : BaseFragment<MoverScreenViewModel, ScreenMoversBinding>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        setupViewBinding(ScreenMoversBinding.inflate(inflater, container, false))

        withViewModel<MoverScreenViewModel> {  }
        return viewBind.root
    }

    override fun setUp() {

    }

}