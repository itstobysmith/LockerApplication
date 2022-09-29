package com.oceangrsmith.mylockerapplication.ui.openlocker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oceangrsmith.mylockerapplication.R
import com.oceangrsmith.mylockerapplication.model.NavigationData
import com.oceangrsmith.mylockerapplication.model.PopUpButtonData
import com.oceangrsmith.mylockerapplication.model.PopUpData
import com.oceangrsmith.mylockerapplication.ui.useraccess.UserAccessViewModel
import com.oceangrsmith.mylockerapplication.utility.BasicString
import com.oceangrsmith.mylockerapplication.utility.SingleString

class OpenLockerViewModel(private val lockerName: String) : ViewModel() {

    private val _popUpData : MutableLiveData<PopUpData?> = MutableLiveData<PopUpData?>()
    private val _navigationData : MutableLiveData<NavigationData> = MutableLiveData<NavigationData>()
    var popUpData : LiveData<PopUpData?> = _popUpData
    var navigationData: LiveData<NavigationData> = _navigationData

    init {
        setUpView()
    }


    private fun setUpView(){
        _popUpData.value = PopUpData(
            title = BasicString("$lockerName $OPEN_LOCKER"),
            titleTopPadding = 30,
            titleBottomPadding = 30,
            titleLeftPadding = 100,
            titleRightPadding = 100,
            titleTextSize = 32f,
            titleColor = R.color.locker_pop_up_success_color,
            subTitle = SingleString(R.string.drop_package_off),
            image = R.drawable.openlocker,
            instructions = SingleString(R.string.tap_done),
            buttonData = listOf(
                PopUpButtonData(
                    SingleString(R.string.done),
                    R.drawable.rounded_button_blue_bg
                )
            )
        )
    }

    fun handlePopUpButtons(value : String){
        when(value){
            UserAccessViewModel.DONE -> navigateToLanding()
            else -> {}
        }
    }

    private fun navigateToLanding() {
      _navigationData.value = NavigationData(R.id.action_openlockerFragment_to_landingFragment)
    }

    companion object {
        const val OPEN_LOCKER = "IS NOW OPEN"
    }

}