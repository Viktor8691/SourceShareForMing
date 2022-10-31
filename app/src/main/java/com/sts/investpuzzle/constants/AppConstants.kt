package com.sts.investpuzzle.constants

const val AUTHORIZATION_PARAMETER = "Authorization"

const val WAFFLE_ERROR_NOT_ANERROR = "WAFFLE_ERROR_NOT_ANERROR"
const val NO_INTERNET = "No Internet"
const val NO_INTERNET_AVAILABLE = "Internet is not available"
const val NETWORK_TIMEOUT: Long = 30
const val CAN_NOT_SIGNUP_TITLE = "Can Not Signup"
const val CAN_NOT_SIGNUP_MESSAGE = "Your signup try is limited, Please try later"
const val COMMON_ERROR_TITLE = "Error"

const val GENDER_ERROR_TITLE = "Gender Error"
const val GENDER_ERROR_MESSAGE = "Please select gender"

const val EDUCATION_ERROR_TITLE = "Education Error"
const val EDUCATION_ERROR_MESSAGE = "Please select education"
const val OTP_WRONG_ERROR_TITLE = "Verify Code Wrong"
const val OTP_WRONG_ERROR_MESSAGE = "Verify code is wrong, please check again"

enum class SendOtpReason {
    LOGIN,
    SIGNUP,
    FORGOT_PASSWORD,
    WIDRAWAL
}

enum class SignInType {
    EMAIL,
    GOOGLE
}
enum class ErrorHandlerCaller {
    NONE,
}
