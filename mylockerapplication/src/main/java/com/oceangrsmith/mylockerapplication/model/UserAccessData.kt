package com.oceangrsmith.mylockerapplication.model

import com.oceangrsmith.mylockerapplication.utility.StringWrapper


data class UserAccessData(val title : StringWrapper = StringWrapper.EMPTY,
                          val subTitle : StringWrapper = StringWrapper.EMPTY,
                          val pinLayoutTitle : StringWrapper = StringWrapper.EMPTY,
                          val numberOfSlot: Int,
                          val buttonText : StringWrapper = StringWrapper.EMPTY)