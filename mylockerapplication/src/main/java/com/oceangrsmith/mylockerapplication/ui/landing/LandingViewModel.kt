package com.oceangrsmith.mylockerapplication.ui.landing

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oceangrsmith.mylockerapplication.repository.ImageLoaderRepository
import com.oceangrsmith.mylockerapplication.R
import com.oceangrsmith.mylockerapplication.model.*
import com.oceangrsmith.mylockerapplication.utility.*


class LandingViewModel(private val imageLoaderRepository: ImageLoaderRepository) : ViewModel() {

    private val _sliderItem = MutableLiveData<IntArray>()
    val sliderItem: LiveData<IntArray> = _sliderItem

    private val _packageLayoutData = MutableLiveData<PackageLayoutData>()
    val packageLayoutData: LiveData<PackageLayoutData> = _packageLayoutData

    private val _navigationData = MutableLiveData<NavigationData>()
    val navigationData: LiveData<NavigationData> = _navigationData


    init {
        setUpPackageLayoutData(
            firstOption = SingleString
                (R.string.pick_a_package),
            secondOption = SingleString(R.string.deliver_a_package),
            commercialImage = R.drawable.humanhandmouth,
            hideHelp = false
        )
        createMockDataForCarousel()
    }

    private fun createMockDataForCarousel() {

        val sampleImages = intArrayOf(
            R.drawable.commercial2,
            R.drawable.commercial3,
            R.drawable.commercial4,
            R.drawable.commercial5,
            R.drawable.commercial6,
            R.drawable.commercial1,
            R.drawable.commercial7
        )
        _sliderItem.value = sampleImages
    }

    private fun setUpPackageLayoutData(
        title: StringWrapper = StringWrapper.EMPTY,
        body: StringWrapper = StringWrapper.EMPTY,
        firstOption: StringWrapper = StringWrapper.EMPTY,
        secondOption: StringWrapper = StringWrapper.EMPTY,
        @DrawableRes commercialImage: Int = 0,
        hideHelp: Boolean = false
    ) {
        _packageLayoutData.value = PackageLayoutData(
            title = title, body = body,
            first_option = firstOption,
            second_option = secondOption,
            commercialImage = commercialImage,
            hideHelp = hideHelp
        )
    }

    fun setPackageLayoutClickListeners(selectedOption: String) {
        when (selectedOption) {
            I_HAVE_PIN -> {
                navigateToPinFragment(
                    Bundle().apply {
                        putString(PIN_NAVIGATION_KEY, I_HAVE_PIN)
                    }
                )
            }
            NO_PIN -> {
                navigateToDeliveryDetailsFragment()
            }
            DELIVER_A_PACKAGE -> setUpPackageLayoutData(
                title = SingleString(R.string.delivery_carrier),
                body = SingleString(R.string.delivery_packages_to_clients),
                firstOption = SingleString
                    (R.string.i_have_pin),
                secondOption = SingleString(R.string.no_pin),
                commercialImage = R.drawable.humanwithbox,
                hideHelp = true
            )
            PICK_A_PACKAGE -> setUpPackageLayoutData(
                firstOption = SingleString
                    (R.string.i_have_pin),
                secondOption = SingleString(R.string.i_have_an_account),
                commercialImage = R.drawable.humanwithbox,
                hideHelp = true
            )
            I_HAVE_AN_ACCOUNT -> navigateToPinFragment(
                Bundle().apply {
                    putString(PIN_NAVIGATION_KEY, I_HAVE_AN_ACCOUNT)
                }
            )
        }
    }

    private fun navigateToPinFragment(bundle: Bundle? = null) {
        _navigationData.value = NavigationData(R.id.action_LandingFragment_to_pinFragment, bundle)
    }

    private fun navigateToDeliveryDetailsFragment() {
        _navigationData.value = NavigationData(R.id.action_LandingFragment_to_pinFragment)
    }

    fun loadImage(
        imageOptions: ImageDownloaderOptions,
        imageView: ImageView,
        context: Context
    ) {
        imageLoaderRepository.displayImage(imageOptions, imageView, context)
    }

}