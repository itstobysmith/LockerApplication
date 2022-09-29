package com.oceangrsmith.mylockerapplication.model

import androidx.annotation.DrawableRes
import com.oceangrsmith.mylockerapplication.utility.StringWrapper

data class PackageLayoutData(
    val title: StringWrapper,
    val body: StringWrapper,
    val first_option: StringWrapper,
    val second_option: StringWrapper,
    @DrawableRes val commercialImage: Int = 0,
    val hideHelp: Boolean = false
)