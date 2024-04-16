package com.reva.revashoppy.network


import com.reva.revashoppy.base.BaseResponse
import com.reva.revashoppy.common.errorHandel.ErrorHandler
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ApiHelperImpl @Inject constructor(private val apiService: ApiService,
                                        private val errorHandler: ErrorHandler
): ApiHelper {

    /* override suspend fun login(loginRequest: LoginRequest): BaseResponse<LoginResponse> {
        return try {
            val login = apiService.login(loginRequest)
            BaseResponse.Success(login)
        } catch (e: Exception) {
            val errorType = errorHandler.getErrorType(e)
            BaseResponse.Error(errorType)
        }
    }*/



}