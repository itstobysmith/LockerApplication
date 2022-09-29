package com.oceangrsmith.mylockerapplication.ui.customviews

import android.content.Context
import android.text.Html
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.button.MaterialButton
import com.oceangrsmith.mylockerapplication.R
import com.oceangrsmith.mylockerapplication.model.PopUpData

class LockerPopUp(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {

    private lateinit var popUpTitle: TextView
    private lateinit var popUpSubTitle: TextView
    private lateinit var popUpImage: ImageView
    private lateinit var popUpInstruction: TextView
    private lateinit var popUpOptionsLayout: LinearLayout
    private lateinit var buttonCLickListener: (String) -> Unit

    init {
        inflate(context, R.layout.locker_popup_layout, this)?.let {
            popUpTitle = it.findViewById(R.id.pop_up_title)
            popUpSubTitle = it.findViewById(R.id.pop_up_subtitle)
            popUpImage = it.findViewById(R.id.pop_up_image)
            popUpInstruction = it.findViewById(R.id.pop_up_instruction)
            popUpOptionsLayout = it.findViewById(R.id.pop_up_options_layout)
        }
    }


    fun setMargins(leftMargin: Int,  rightMargin: Int, topMargin: Int, bottomMargin: Int){
        val params = this.layoutParams as RelativeLayout.LayoutParams
        params.setMargins(leftMargin,topMargin,rightMargin,bottomMargin)
        this.layoutParams = params
    }

    private fun setUpView(data: PopUpData) {
        data.let { popUpData ->

            popUpTitle.text = popUpData.title.getFormattedString(context)
            popUpTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, popUpData.titleTextSize)
            popUpTitle.setPadding(
                popUpData.titleLeftPadding, popUpData.titleTopPadding,
                popUpData.titleRightPadding, popUpData.titleBottomPadding
            )
            popUpTitle.setBackgroundResource(popUpData.titleColor)
            popUpTitle.setPadding(
                popUpData.titleLeftPadding, popUpData.titleTopPadding,
                popUpData.titleRightPadding, popUpData.titleBottomPadding
            )
            popUpData.image?.let { image ->
                popUpImage.visibility = View.VISIBLE
                popUpImage.setBackgroundResource(image)
            }
            popUpSubTitle.text = Html.fromHtml(
                popUpData.subTitle.getFormattedString(context),
                Html.FROM_HTML_MODE_LEGACY
            )

            val instructions = Html.fromHtml(
                popUpData.instructions.getFormattedString(context),
                Html.FROM_HTML_MODE_LEGACY
            )
            if(!instructions.isNullOrEmpty())   {
                popUpInstruction.visibility = View.VISIBLE
                    popUpInstruction.text = instructions
            }
            popUpOptionsLayout.removeAllViews()
            popUpData.buttonData?.let { buttonData ->
                buttonData.forEach {
                    val button = MaterialButton(context)
                    button.apply {
                        layoutParams = LayoutParams(
                            LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT
                        )
                        setPadding(30,0,30,0)
                        setBackgroundResource(it.background)
                        text = it.value.getFormattedString(context)
                        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F)
                        val typeface = ResourcesCompat.getFont(context,R.font.roboto_bold)
                        setTypeface(typeface)
                        textAlignment = TEXT_ALIGNMENT_CENTER
                        setTextColor(context.resources.getColor(R.color.locker_white_color))
                        gravity = Gravity.CENTER
                        backgroundTintList = null
                        button.setOnClickListener {
                            buttonCLickListener.invoke(text.toString())
                        }
                        popUpOptionsLayout.addView(this)
                    }
                }
            }
        }
    }

    fun setData(data: PopUpData, clickListener: (String) -> Unit) {
        setUpView(data)
        buttonCLickListener = clickListener
    }

}