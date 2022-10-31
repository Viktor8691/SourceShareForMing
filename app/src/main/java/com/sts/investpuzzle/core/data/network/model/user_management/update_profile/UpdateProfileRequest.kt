package com.sts.investpuzzle.core.data.network.model.user_management.update_profile

data class UpdateProfileRequest (
    var bio : String = "",
    var photoUrl : String = "",
    var pronounsId : String = "",
    var educationId : String = "",
    var otherPronouns : String = "",
    var otherEducation : String = "",
    var userFullName : String = ""
)