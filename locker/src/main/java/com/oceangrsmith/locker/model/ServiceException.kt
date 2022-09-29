package com.example.envoyapplication.model

import com.oceangrsmith.locker.utility.StringWrapper
import com.oceangrsmith.locker.utility.asStringWrapper

data class ServiceException(val code: String?, val string: StringWrapper) {
    constructor(code: String?, string: String) : this(code, string.asStringWrapper())
}

