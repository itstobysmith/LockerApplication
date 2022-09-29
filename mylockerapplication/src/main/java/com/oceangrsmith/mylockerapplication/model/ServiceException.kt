package com.oceangrsmith.mylockerapplication.model

import com.oceangrsmith.mylockerapplication.utility.StringWrapper
import com.oceangrsmith.mylockerapplication.utility.asStringWrapper


data class ServiceException(val code: String?, val string: StringWrapper) {
    constructor(code: String?, string: String) : this(code, string.asStringWrapper())
}

