package com.reva.revashoppy.common.errorHandel

sealed class BaseError {
    object NetworkError : BaseError()
    object UnauthorizedError : BaseError()
    data class ServerError(val responseBody: String?) : BaseError()
    object UnknownError : BaseError()
}
