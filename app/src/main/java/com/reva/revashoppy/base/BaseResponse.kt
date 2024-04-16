package com.reva.revashoppy.base

import com.reva.revashoppy.network.ResponseModel
import com.reva.revashoppy.common.errorHandel.BaseError


sealed class BaseResponse<T> {

    data class Success<T>(val data: T) : BaseResponse<T>()
    data class SuccessL<T>(val data: ResponseModel<T>) : BaseResponse<ResponseModel<T>>()
    data class SuccessR<T>(val data: ResponseModel<ArrayList<T>>) : BaseResponse<ResponseModel<ArrayList<T>>>()
    data class Error<T>(val error: BaseError) : BaseResponse<T>()
    data class ErrorL<T>(val error: BaseError) : BaseResponse<ResponseModel<T>>()
    data class ErrorR<T>(val error: BaseError) : BaseResponse<ResponseModel<ArrayList<T>>>()

}