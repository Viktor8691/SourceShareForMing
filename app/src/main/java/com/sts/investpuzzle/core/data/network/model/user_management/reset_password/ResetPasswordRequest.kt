package com.sts.investpuzzle.core.data.network.model.user_management.reset_password

data class ResetPasswordRequest(
    val email : String = "",
    val password : String = "",
    val code : String = ""
)