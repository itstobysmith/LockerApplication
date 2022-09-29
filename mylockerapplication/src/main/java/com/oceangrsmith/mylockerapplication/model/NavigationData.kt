package com.oceangrsmith.mylockerapplication.model

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.IdRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NavigationData(@IdRes val id : Int, val bundle : Bundle? = null) : Parcelable
