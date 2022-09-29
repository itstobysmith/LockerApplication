package com.oceangrsmith.mylockerapplication.ui.deliveryDetailsFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oceangrsmith.mylockerapplication.model.NavigationData
import com.oceangrsmith.mylockerapplication.model.PopUpData
import com.oceangrsmith.mylockerapplication.ui.openlocker.OpenLockerFragment

class DeliveryDetailsViewModel : ViewModel() {

    private val _navigationData = MutableLiveData<NavigationData>()
    val navigationData: LiveData<NavigationData> = _navigationData

    private val _popUpData = MutableLiveData<PopUpData>()
    val popUpData: LiveData<PopUpData> = _popUpData

   private fun verifyDetailsInformation(){
          if(true){

          } else {

          }
    }

    fun navigateToOpenLockerScreen() {
        _navigationData.value = OpenLockerFragment().createNavigationData("LOCKER 8")
    }

}