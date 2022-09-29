package com.oceangrsmith.locker.model

import com.example.envoyapplication.model.ServiceException

/**
 * A sealed class to wrap a response object to return either a success, or error object.
 */
sealed class ServiceResult<out R> {
    data class Success<out T>(val data: T) : ServiceResult<T>()
    data class Error(val exception: ServiceException) : ServiceResult<Nothing>()
}
