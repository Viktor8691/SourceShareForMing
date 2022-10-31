package com.sts.investpuzzle.features.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sts.investpuzzle.base.BaseFragment
import com.sts.investpuzzle.databinding.ScreenProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileScreen : BaseFragment<ProfileScreenViewModel, ScreenProfileBinding>(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        setupViewBinding(ScreenProfileBinding.inflate(inflater, container, false))

        withViewModel<ProfileScreenViewModel> {  }
        return viewBind.root
    }

    override fun setUp() {

    }
}