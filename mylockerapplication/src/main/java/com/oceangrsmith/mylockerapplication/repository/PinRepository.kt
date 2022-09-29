package com.oceangrsmith.mylockerapplication.repository

import android.content.Context
import com.oceangrsmith.mylockerapplication.model.PinVerificationData
import com.oceangrsmith.mylockerapplication.model.UserAccessData

interface PinRepository {

    suspend fun getPinFragmentData(pinNavigationKey : String): UserAccessData

    suspend fun verifyPackagePin(pin: String): PinVerificationData

    suspend fun verifyAccountPinAndPhoneNumber(phoneNumber: String, pin: String ): PinVerificationData

    companion object{
        fun getPinRepository(context: Context) : PinRepository = PinRepositoryImpl()
    }
}