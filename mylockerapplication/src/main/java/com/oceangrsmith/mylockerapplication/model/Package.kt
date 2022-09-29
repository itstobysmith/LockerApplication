package com.oceangrsmith.mylockerapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Package(val name: String, val boxNumber: Int, val pcbNumber: Int ) : Parcelable