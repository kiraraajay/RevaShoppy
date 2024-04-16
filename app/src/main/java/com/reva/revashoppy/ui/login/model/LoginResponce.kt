package com.reva.revashoppy.ui.login.model

data class LoginResponce(
    val designation: String,
    val email: String,
    val name: String,
    val mobile: String,
    val status: Boolean,
    val userId: String,
    val msg: String

)