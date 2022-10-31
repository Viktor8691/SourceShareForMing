package com.sts.investpuzzle.core.data.network.model.user_management.user_info

data class UserInfoRequest(
    var password : String = "",
    var pronounsId : String = "",
    var yearofBirth : String = "",
    var countryId : String = "",
    var educationId : String = "",
    var loginType : Int = 0,
    var socialId : String = "",
    var otherPronouns : String = "",
    var otherEducation : String = ""
)
