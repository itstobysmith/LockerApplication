package com.oceangrsmith.mylockerapplication.model

import com.oceangrsmith.mylockerapplication.utility.StringWrapper

data class ErrorMessage(val title: StringWrapper, val message: StringWrapper,
                        val navigateToPhoneEntry: Boolean = false)