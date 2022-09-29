package com.oceangrsmith.mylockerapplication.ui.openlocker

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.oceangrsmith.mylockerapplication.R
import com.oceangrsmith.mylockerapplication.model.PopUpData
import com.oceangrsmith.mylockerapplication.ui.customviews.LockerPopUp
import com.oceangrsmith.mylockerapplication.utility.ViewComponent
import kotlin.math.abs

internal class OpenLockerComponent(
    viewModel: OpenLockerViewModel,
    val context: Context, private val navController: NavController
) : ViewComponent<OpenLockerViewModel>(viewModel, context) {

    lateinit var lockerPopUp: LockerPopUp
    lateinit var thumbsUpImage: ImageView

    override fun attachView(container: View) {
        lockerPopUp = container.findViewById(R.id.locker_popup)
        thumbsUpImage = container.findViewById(R.id.thumbs_up_image)
    }

    override fun attachLifecycleOwner(owner: LifecycleOwner) {
        viewModel.popUpData.observe(owner, {
            it?.let {
                setUpView(it)
            }
        })

        viewModel.navigationData.observe(owner, {
            it?.let {
                navController.navigate(it.id)
            }
        })
    }

    private fun setUpView(data: PopUpData) {
        thumbsUpImage.setImageResource(R.drawable.manthumbsup)
        setUpImageOverlay()
        lockerPopUp.setData(data, clickListener = {
            viewModel.handlePopUpButtons(it)
        })
    }

    private fun setUpImageOverlay() {
        val currentWidth = thumbsUpImage.drawable.intrinsicWidth
        val margin = currentWidth - abs((currentWidth / 4))
        lockerPopUp.setMargins(margin,0,40,40)
    }

}