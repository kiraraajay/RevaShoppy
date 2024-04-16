package com.reva.revashoppy.common

import com.reva.revashoppy.ui.login.model.LoginResponce


sealed class AppState {
    data object Loading : AppState()
    data object NoInternetConnection : AppState()
    data object UnknownError : AppState()

    data class SeverError(val message: String) : AppState()
    data class APISuccess(val login: String) : AppState()
    data class APILoginSuccess(val login: LoginResponce) : AppState()




}