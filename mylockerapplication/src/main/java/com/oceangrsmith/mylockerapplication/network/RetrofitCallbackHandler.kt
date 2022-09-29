package com.oceangrsmith.mylockerapplication.network


import com.oceangrsmith.mylockerapplication.R
import com.oceangrsmith.mylockerapplication.logger.Logger
import com.oceangrsmith.mylockerapplication.model.NoConnectivityException
import com.oceangrsmith.mylockerapplication.model.ServiceException
import com.oceangrsmith.mylockerapplication.model.ServiceResult
import com.oceangrsmith.mylockerapplication.utility.StringWrapper
import com.oceangrsmith.mylockerapplication.utility.asSingleString
import retrofit2.Response

object RetrofitCallbackHandler {

    suspend fun <T> processCall(
        call: suspend () -> Response<T>
    ) : ServiceResult<T> {
        val genericMessage = R.string.generic_service_error_message.asSingleString()
        return try {

            val serviceCallback = call()
            val body = serviceCallback.body()
            if (serviceCallback.isSuccessful && body != null) {
                ServiceResult.Success(body)
            } else {
                processGenericExceptionMessage(
                    serviceCallback.message().orEmpty(),
                    serviceCallback.code().toString()
                )
                getGenericServiceError("${serviceCallback.code()}", genericMessage)
            }

        } catch (exception: Exception) {
            when (exception) {
                is NoConnectivityException -> {
                    processGenericExceptionMessage("No Connectivity", null)
                    getConnectivityServiceError()
                }
                else -> {
                    processGenericExceptionMessage(exception.localizedMessage ?: "", null)
                    getGenericServiceError(null, genericMessage)
                }
            }
        }
    }

    private fun getConnectivityServiceError(): ServiceResult.Error {
        val error = R.string.generic_connectivity_error_message.asSingleString()
        return ServiceResult.Error(ServiceException(null, error))
    }

    private fun getGenericServiceError(
        errorCode: String?,
        errorMessage: StringWrapper
    ): ServiceResult.Error {
        return ServiceResult.Error(ServiceException(errorCode, errorMessage))
    }

    private fun processGenericExceptionMessage(message: String, code: String?) {
        val codeMessage = if (code == null) "" else "with code: $code; "
        Logger.e("$codeMessage$message")
    }
}
