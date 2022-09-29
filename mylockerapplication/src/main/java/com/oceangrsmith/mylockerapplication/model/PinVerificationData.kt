package com.oceangrsmith.mylockerapplication.model


data class PinVerificationData(
    val success: Boolean,
    val errorMessage: ErrorMessage? = null,
    val deliveryDetail: Boolean = false,
    val packageBox: Boolean = false,
    val listPackages: ArrayList<Package>? = null
)