package com.oceangrsmith.mylockerapplication.ui.useraccess

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oceangrsmith.mylockerapplication.R
import com.oceangrsmith.mylockerapplication.model.*
import com.oceangrsmith.mylockerapplication.repository.PinRepository
import com.oceangrsmith.mylockerapplication.ui.customviews.KeypadEntry
import java.lang.StringBuilder
import com.oceangrsmith.mylockerapplication.ui.customviews.KeypadEntry.ONE
import com.oceangrsmith.mylockerapplication.ui.customviews.KeypadEntry.THREE
import com.oceangrsmith.mylockerapplication.ui.customviews.KeypadEntry.TWO
import com.oceangrsmith.mylockerapplication.ui.customviews.KeypadEntry.EIGHT
import com.oceangrsmith.mylockerapplication.ui.customviews.KeypadEntry.FIVE
import com.oceangrsmith.mylockerapplication.ui.customviews.KeypadEntry.FOUR
import com.oceangrsmith.mylockerapplication.ui.customviews.KeypadEntry.SEVEN
import com.oceangrsmith.mylockerapplication.ui.customviews.KeypadEntry.SIX
import com.oceangrsmith.mylockerapplication.ui.customviews.KeypadEntry.NINE
import com.oceangrsmith.mylockerapplication.ui.customviews.KeypadEntry.CLEAR
import com.oceangrsmith.mylockerapplication.ui.customviews.KeypadEntry.ZERO
import com.oceangrsmith.mylockerapplication.model.Package
import com.oceangrsmith.mylockerapplication.utility.*
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

class UserAccessViewModel(private val initialNavigationKey: String, val pinRepository: PinRepository) :
    ViewModel() {

    //livedata to display popup dialog
    private val _popUpData = MutableLiveData<PopUpData?>()
    val popUpData: LiveData<PopUpData?> = _popUpData

    //livedata to navigate
    private val _navigationData = MutableLiveData<NavigationData>()
    val navigationData: LiveData<NavigationData> = _navigationData

    //livedata to setup view
    private val _userAccessData = MutableLiveData<UserAccessData>()
    val userAccessData: LiveData<UserAccessData> = _userAccessData


    //livedata to show current passcode typed by user
    private val _currentNumber = MutableLiveData<String>()
    val currentNumber: LiveData<String> = _currentNumber

    var currentPinNavigationKey = initialNavigationKey

    init {
        setUpUserAccessView()
    }

    var pinNumberStringBuilder = StringBuilder()
    var phoneNumberStringBuilder = StringBuilder()

    private fun setUpUserAccessView() {
        viewModelScope.launch {
            _userAccessData.value = pinRepository.getPinFragmentData(currentPinNavigationKey)
        }
    }

    private fun verifyPin() {
        viewModelScope.launch {
            when (currentPinNavigationKey) {
                I_HAVE_AN_ACCOUNT -> {
                    _userAccessData.value = UserAccessData(
                        title = SingleString(R.string.input_account_pin),
                        subTitle = SingleString(R.string.login_to_your_account),
                        pinLayoutTitle = SingleString(R.string.enter_your_account_pin),
                        numberOfSlot = 5
                    )
                    currentPinNavigationKey = I_HAVE_PIN
                }
                I_HAVE_PIN -> {
                    val result = when {
                        phoneNumberStringBuilder.toString().isNotEmpty() ->
                            pinRepository.verifyAccountPinAndPhoneNumber(
                                phoneNumberStringBuilder.toString(),
                                pinNumberStringBuilder.toString()
                            )
                        else -> pinRepository.verifyPackagePin(pinNumberStringBuilder.toString())
                    }
                    if (!result.success) {
                        currentPinNavigationKey = initialNavigationKey
                        result.errorMessage?.let { displayWrongDetailsPopUp(it) }
                    } else {
                        result.let {
                            navigateToNextScreen(it)
                        }
                    }
                }
            }
        }
    }

    private suspend fun displayWrongDetailsPopUp(errorMessage: ErrorMessage) {
        pinNumberStringBuilder.clear()
        phoneNumberStringBuilder.clear()
        _popUpData.value = PopUpData(
            title = errorMessage.title,
            titleBottomPadding = 30,
            titleTopPadding = 30,
            titleLeftPadding = 180,
            titleRightPadding = 180,
            titleTextSize = 26F,
            titleColor = R.color.locker_pop_up_failure_color,
            subTitle = errorMessage.message,
            image = null,
            instructions = BasicString(""),
            buttonData = listOf(
                PopUpButtonData(
                    SingleString(R.string.cancel),
                    R.drawable.rounded_button_red_bg
                )
            )
        )
        _userAccessData.value = pinRepository.getPinFragmentData(currentPinNavigationKey)
    }

    fun handlePopUpButtons(value: String) {
        when (value) {
            CANCEL -> removePopUp()
            CONTINUE -> navigateToDeliveryDetails()
            DONE -> {}
            else -> {}
        }
    }

    fun inputNumber(value: KeypadEntry) {
        when (value) {
            ZERO -> appendNumber(0)
            ONE -> appendNumber(1)
            TWO -> appendNumber(2)
            THREE -> appendNumber(3)
            FOUR -> appendNumber(4)
            FIVE -> appendNumber(5)
            SIX -> appendNumber(6)
            SEVEN -> appendNumber(7)
            EIGHT -> appendNumber(8)
            NINE -> appendNumber(9)
            KeypadEntry.DONE -> {
                verifyPin()
                return
            }
            CLEAR -> {
                when (currentPinNavigationKey) {
                    I_HAVE_AN_ACCOUNT -> {
                        if (phoneNumberStringBuilder.isNotEmpty()) {
                            phoneNumberStringBuilder.deleteCharAt(phoneNumberStringBuilder.length - 1)
                        }
                    }
                    I_HAVE_PIN -> {
                        if (pinNumberStringBuilder.isNotEmpty()) {
                            pinNumberStringBuilder.deleteCharAt(pinNumberStringBuilder.length - 1)
                        }
                    }
                }

            }
        }
        _currentNumber.value = when (currentPinNavigationKey) {
            I_HAVE_PIN -> pinNumberStringBuilder.toString()
            I_HAVE_AN_ACCOUNT -> phoneNumberStringBuilder.toString()
            else -> null
        }
    }

    //this function appends each number of the passcode a user enters
    private fun appendNumber(value: Int) {
        when (currentPinNavigationKey) {
            I_HAVE_PIN -> {
                if (pinNumberStringBuilder.length < 5) {
                    pinNumberStringBuilder.append(value)
                }
            }
            I_HAVE_AN_ACCOUNT -> {
                if (phoneNumberStringBuilder.length < 11) {
                    phoneNumberStringBuilder.append(value)
                }
            }
        }
    }

    private fun navigateToDeliveryDetails() {
        _navigationData.value = NavigationData(R.id.action_pinFragment_to_DeliveryDetailsFragment)
    }

    private fun navigateToPackageBoxes(packages: ArrayList<Package>?) {
        if (!packages.isNullOrEmpty()) {
            val bundle = Bundle().apply {
                putParcelableArrayList(DETAILS_OF_PACKAGES_AVAILABLE, packages)
            }
            _navigationData.value =
                NavigationData(R.id.action_pinFragment_to_PackageBoxFragment, bundle)
        } else
            _navigationData.value =
                NavigationData(R.id.action_pinFragment_to_PackageBoxFragment)
    }

    private fun removePopUp() {
        pinNumberStringBuilder.clear()
        _popUpData.value = null
    }

    private fun navigateToNextScreen(data: PinVerificationData) {
        when {
            data.deliveryDetail -> navigateToDeliveryDetails()
            data.packageBox -> navigateToPackageBoxes(data.listPackages)
        }
    }

    companion object {
        const val CANCEL = "CANCEL"
        const val CONTINUE = "CONTINUE"
        const val DONE = "DONE"
    }
}