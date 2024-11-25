package com.android.test.mangareader.data.model

data class UserDTO(
    val id: Int,
    val username: String,
    val email: String,
    val token: String?,
    var userProfile: UserProfileDTO?,
)
