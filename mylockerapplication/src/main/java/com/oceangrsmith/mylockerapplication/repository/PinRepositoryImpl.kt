package com.oceangrsmith.mylockerapplication.repository

import com.oceangrsmith.mylockerapplication.R
import com.oceangrsmith.mylockerapplication.model.ErrorMessage
import com.oceangrsmith.mylockerapplication.model.Package
import com.oceangrsmith.mylockerapplication.model.PinVerificationData
import com.oceangrsmith.mylockerapplication.model.UserAccessData
import com.oceangrsmith.mylockerapplication.utility.BasicString
import com.oceangrsmith.mylockerapplication.utility.I_HAVE_AN_ACCOUNT
import com.oceangrsmith.mylockerapplication.utility.SingleString

class PinRepositoryImpl : PinRepository {

    override suspend fun getPinFragmentData(pinNavigationKey: String) =
        // we should get data from the local data base
        when (pinNavigationKey) {
            I_HAVE_AN_ACCOUNT -> UserAccessData(
                title = SingleString(R.string.login),
                subTitle = SingleString(R.string.login_to_your_account),
                pinLayoutTitle = SingleString(R.string.enter_your_account_phone_number),
                numberOfSlot = 11,
                buttonText = SingleString(R.string.submit)
            )
            else -> UserAccessData(
                title = SingleString(R.string.input_package_pin),
                pinLayoutTitle = SingleString(R.string.enter_package_pin_to_deliver_your_package),
                numberOfSlot = 5,
                buttonText = SingleString(R.string.submit)
            )
        }

    override suspend fun verifyPackagePin(pin: String): PinVerificationData {
        return PinVerificationData(
            success = false, errorMessage =
            ErrorMessage(
                title = SingleString(R.string.incorrect_pin),
                message = SingleString(R.string.wrong_delivery_pin_message),
                navigateToPhoneEntry = false
            ),
            deliveryDetail = true
        )
    }

    override suspend fun verifyAccountPinAndPhoneNumber(
        phoneNumber: String,
        pin: String
    ): PinVerificationData {
        return PinVerificationData(
            success = true, errorMessage =
            ErrorMessage(
                title = SingleString(R.string.incorrent_details),
                message = SingleString(R.string.wrong_pickup_pin_or_phone_no_message),
                navigateToPhoneEntry = true
            ), packageBox = true,
            listPackages = arrayListOf(
                Package("box 1", boxNumber = 1, pcbNumber = 1),
                Package("box 2", boxNumber = 2, pcbNumber = 2),
                Package("box 3", boxNumber = 3, pcbNumber = 3),
                Package("box 3", boxNumber = 3, pcbNumber = 3),
                Package("box 3", boxNumber = 3, pcbNumber = 3)
            )
        )
    }


}