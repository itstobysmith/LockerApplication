package com.oceangrsmith.mylockerapplication.model

sealed class DeliveryDetail {

    data class UnitNumber(val number : Int) : DeliveryDetail()
    data class Courier(val courierService: String) : DeliveryDetail()
}