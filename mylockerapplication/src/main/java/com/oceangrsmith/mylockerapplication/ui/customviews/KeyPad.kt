package com.oceangrsmith.mylockerapplication.ui.customviews

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.oceangrsmith.mylockerapplication.R
import com.oceangrsmith.mylockerapplication.ui.customviews.KeypadEntry.ONE
import com.oceangrsmith.mylockerapplication.ui.customviews.KeypadEntry.THREE
import com.oceangrsmith.mylockerapplication.ui.customviews.KeypadEntry.TWO
import com.oceangrsmith.mylockerapplication.ui.customviews.KeypadEntry.EIGHT
import com.oceangrsmith.mylockerapplication.ui.customviews.KeypadEntry.FIVE
import com.oceangrsmith.mylockerapplication.ui.customviews.KeypadEntry.FOUR
import com.oceangrsmith.mylockerapplication.ui.customviews.KeypadEntry.SEVEN
import com.oceangrsmith.mylockerapplication.ui.customviews.KeypadEntry.SIX
import com.oceangrsmith.mylockerapplication.ui.customviews.KeypadEntry.NINE
import com.oceangrsmith.mylockerapplication.ui.customviews.KeypadEntry.DONE
import com.oceangrsmith.mylockerapplication.ui.customviews.KeypadEntry.CLEAR
import com.oceangrsmith.mylockerapplication.ui.customviews.KeypadEntry.ZERO

class KeyPad(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {


    private lateinit var oneImage: ImageView
    private lateinit var twoImage: ImageView
    private lateinit var threeImage: ImageView
    private lateinit var fourImage: ImageView
    private lateinit var fiveImage: ImageView
    private lateinit var sixImage: ImageView
    private lateinit var sevenImage: ImageView
    private lateinit var eightImage: ImageView
    private lateinit var nineImage: ImageView
    private lateinit var zeroImage: ImageView
    private lateinit var doneImage: ImageView
    private lateinit var clearImage: ImageView


    private lateinit var numberClickListener: (KeypadEntry) -> Unit

    init {
        inflate(context, R.layout.keyboard_layout, this)?.let {
            oneImage = it.findViewById(R.id.one)
            twoImage = it.findViewById(R.id.two)
            threeImage = it.findViewById(R.id.three)
            fourImage = it.findViewById(R.id.four)
            fiveImage = it.findViewById(R.id.five)
            sixImage = it.findViewById(R.id.six)
            sevenImage = it.findViewById(R.id.seven)
            eightImage = it.findViewById(R.id.eight)
            nineImage = it.findViewById(R.id.nine)
            zeroImage = it.findViewById(R.id.zero)
            doneImage = it.findViewById(R.id.done)
            clearImage = it.findViewById(R.id.clear)
        }
    }


    private fun setUpViews() {
        oneImage.setOnClickListener {
            numberClickListener.invoke(ONE)
        }
        twoImage.setOnClickListener {
            numberClickListener.invoke(TWO)
        }
        threeImage.setOnClickListener {
            numberClickListener.invoke(THREE)
        }
        fourImage.setOnClickListener {
            numberClickListener.invoke(FOUR)
        }
        fiveImage.setOnClickListener {
            numberClickListener.invoke(FIVE)
        }
        sixImage.setOnClickListener {
            numberClickListener.invoke(SIX)
        }
        sevenImage.setOnClickListener {
            numberClickListener.invoke(SEVEN)
        }
        eightImage.setOnClickListener {
            numberClickListener.invoke(EIGHT)
        }
        nineImage.setOnClickListener {
            numberClickListener.invoke(NINE)
        }
        zeroImage.setOnClickListener {
            numberClickListener.invoke(ZERO)
        }
        doneImage.setOnClickListener {
            numberClickListener.invoke(DONE)
        }
        clearImage.setOnClickListener {
            numberClickListener.invoke(CLEAR)
        }
    }


    fun setClickListener(clickListener: (KeypadEntry) -> Unit) {
        numberClickListener = clickListener
        setUpViews()
    }

}