package com.sts.investpuzzle.core.data.network.model.user_management.signin

import com.google.gson.annotations.SerializedName

data class SignInRequest (
        val email : String = "",
        val password : String = "",
        val loginType : Int = 0,
        val socialId : String = "")