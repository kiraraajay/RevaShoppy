package com.reva.revashoppy.common.errorHandel

import com.reva.revashoppy.common.errorHandel.BaseError

interface ErrorHandler {
    fun getErrorType(throwable: Throwable): BaseError
}