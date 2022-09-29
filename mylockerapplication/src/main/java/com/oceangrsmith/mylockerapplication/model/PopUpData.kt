package com.oceangrsmith.mylockerapplication.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.oceangrsmith.mylockerapplication.utility.StringWrapper

data class PopUpData(val title: StringWrapper = StringWrapper.EMPTY, @ColorRes val titleColor : Int = 0, val subTitle: StringWrapper = StringWrapper.EMPTY,
                     @DrawableRes val image : Int? = null, val instructions : StringWrapper = StringWrapper.EMPTY,
                     val buttonData : List<PopUpButtonData>? = emptyList(), val titleTopPadding : Int = 0,
                     val titleTextSize : Float = 16F,
                     val titleBottomPadding : Int = 0, val titleLeftPadding : Int = 0, val titleRightPadding : Int = 0)


data class PopUpButtonData(val value: StringWrapper = StringWrapper.EMPTY, @DrawableRes val background : Int)