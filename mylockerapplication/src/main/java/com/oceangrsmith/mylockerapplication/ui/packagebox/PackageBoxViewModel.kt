package com.oceangrsmith.mylockerapplication.ui.packagebox

import androidx.lifecycle.ViewModel
import com.oceangrsmith.mylockerapplication.model.Package

class PackageBoxViewModel(val packages : ArrayList<Package>?) : ViewModel() {
    

    fun handleClickListeners( data: String){
        when(data){
            OPEN_ALL_BOXES -> openAllBoxes()
            DONE -> navigateToLandingScreen()
        }
    }

    private fun openAllBoxes(){

    }

    private fun navigateToLandingScreen(){

    }

    companion object{
        private const val OPEN_ALL_BOXES = "OPEN ALL BOXES"
        private const val DONE = "DONE"
    }
}