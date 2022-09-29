package com.oceangrsmith.mylockerapplication.ui.useraccess

import android.content.Context
import android.text.SpannableStringBuilder
import android.view.View
import android.view.View.*
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.chaos.view.PinView
import com.oceangrsmith.mylockerapplication.R
import com.oceangrsmith.mylockerapplication.model.PopUpData
import com.oceangrsmith.mylockerapplication.ui.customviews.KeyPad
import com.oceangrsmith.mylockerapplication.ui.customviews.LockerPopUp
import com.oceangrsmith.mylockerapplication.utility.ViewComponent

internal class UserAccessComponent(
    userAccessViewModel: UserAccessViewModel,
    val context: Context,
    private val navController: NavController
) : ViewComponent<UserAccessViewModel>(userAccessViewModel, context) {

    private var currentNumber: String = ""
    private lateinit var userAccessTitle: TextView
    private lateinit var userAccessSubTitle: TextView
    private lateinit var pinLayoutTitle: TextView
    private lateinit var pinCodeLayout: PinView
    private lateinit var pinBackArrow: ImageView
    private lateinit var lockerPopUp: LockerPopUp
    private lateinit var keyPad: KeyPad
    private lateinit var passcodeEditText: EditText

    override fun attachView(container: View) {
        container.let {
            userAccessTitle = it.findViewById(R.id.user_access_title)
            userAccessSubTitle = it.findViewById(R.id.user_access_subtitle)
            pinBackArrow = it.findViewById(R.id.pin_back_arrow)
            pinCodeLayout = it.findViewById(R.id.pin_layout)
            lockerPopUp = it.findViewById(R.id.locker_popup)
            keyPad = it.findViewById(R.id.keypad)
            passcodeEditText = EditText(context)
            pinLayoutTitle = it.findViewById(R.id.pin_layout_title)
        }
        setOnClickListener()
    }

    override fun attachLifecycleOwner(owner: LifecycleOwner) {
        viewModel.popUpData.observe(owner, {
            when (it) {
                null -> {
                    lockerPopUp.visibility = GONE
                    pinCodeLayout.text = SpannableStringBuilder("")
                    currentNumber = ""
                }
                else -> {
                    setPopUpDialog(it)
                }
            }
        })

        viewModel.navigationData.observe(owner, {
            it?.let {
                lockerPopUp.findNavController().navigate(it.id, it.bundle)
            }
        })

        viewModel.userAccessData.observe(owner, {
            it?.let {
                userAccessTitle.text = it.title.getFormattedString(context)
                if (it.subTitle.getFormattedString(context).isNotEmpty()){
                    userAccessSubTitle.visibility = VISIBLE
                    userAccessSubTitle.text = it.subTitle.getFormattedString(context)
                } else{
                    userAccessSubTitle.visibility = GONE
                }
                pinLayoutTitle.text = it.pinLayoutTitle.getFormattedString(context)
                pinCodeLayout.itemCount = it.numberOfSlot
                pinCodeLayout.invalidate()
                pinCodeLayout.requestLayout()
                currentNumber = ""
                pinCodeLayout.text = SpannableStringBuilder("")
            }
        })

        viewModel.currentNumber.observe(owner, {
            it?.let {
                currentNumber = it
                pinCodeLayout.text = SpannableStringBuilder(currentNumber)
            }
        })
    }

    private fun setOnClickListener() {
        setPinCodeLayoutListener()
        pinBackArrow.setOnClickListener {
            navController.popBackStack(R.id.LandingFragment, true)
        }
    }

    private fun setPinCodeLayoutListener() {
        hideAndroidKeyboard()
        keyPad.setClickListener { number ->
            viewModel.inputNumber(number)
        }
    }

    private fun hideAndroidKeyboard() {
        pinCodeLayout.showSoftInputOnFocus = false
    }

    private fun setPopUpDialog(data: PopUpData) {
        lockerPopUp.setData(data, clickListener = {
            viewModel.handlePopUpButtons(it)
        })
        lockerPopUp.visibility = VISIBLE
    }
}