package com.oceangrsmith.mylockerapplication.ui.landing

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.google.android.material.button.MaterialButton
import com.oceangrsmith.mylockerapplication.R
import com.oceangrsmith.mylockerapplication.utility.ViewComponent
import com.synnapps.carouselview.CarouselView

internal class LandingComponent(viewModel: LandingViewModel, val context: Context, val navController: NavController) :
    ViewComponent<LandingViewModel>(viewModel,context) {

    lateinit var carouselView : CarouselView
    private lateinit var packageTitle: TextView
    private lateinit var packageBody: TextView
    private lateinit var pickPackageOption: Button
    private lateinit var deliverPackageOption: Button
    private lateinit var packageLayoutContainer: FrameLayout
    private lateinit var packageLayout: View
    private lateinit var packageBackArrow: ImageView
    private lateinit var needHelp: TextView
    private lateinit var commercialImage: ImageView

    override fun attachView(container: View) {
         carouselView = container.findViewById(R.id.commercials_carouselView)
         commercialImage = container.findViewById(R.id.commercials_image)
         packageLayoutContainer = container.findViewById(R.id.package_option_container)
         setUpPackageView()
         setClickListeners()
    }

    override fun attachLifecycleOwner(owner: LifecycleOwner) {
        viewModel.sliderItem.observe(owner, {
            it?.let {
                carouselView.apply{
                    setImageListener { position, imageView ->
                        imageView.setImageResource(it[position])
                    }
                    pageCount  = it.size
                }
            }
        })

        viewModel.packageLayoutData.observe(owner, {
            it?.let {
                when{ it.title.getFormattedString(context).isNotEmpty() &&
                it.body.getFormattedString(context).isNotEmpty()-> {
                        packageBackArrow.visibility = View.VISIBLE
                        packageTitle.visibility = View.VISIBLE
                        packageBody.visibility = View.VISIBLE
                        packageTitle.text = it.title.getFormattedString(context)
                        packageBody.text = it.body.getFormattedString(context)
                    }
                    else -> {}
                }

                when(it.hideHelp){
                    true -> needHelp.visibility = View.GONE
                    else ->needHelp.visibility = View.VISIBLE
                }
                commercialImage.setBackgroundResource(it.commercialImage)
                pickPackageOption.text = it.first_option.getFormattedString(context)
                deliverPackageOption.text = it.second_option.getFormattedString(context)
            }
        })

        viewModel.navigationData.observe(owner, {
            it?.let {
                navController.navigate(it.id,it.bundle)
            }
        })
    }

    private fun setUpPackageView() {
         packageLayout =  LayoutInflater.from(context).inflate(R.layout.package_layout,null,false)
         packageTitle = packageLayout.findViewById(R.id.package_title)
         packageBody = packageLayout.findViewById(R.id.package_body)
         pickPackageOption = packageLayout.findViewById(R.id.pick_option_button)
         deliverPackageOption = packageLayout.findViewById(R.id.deliver_option_button)
         needHelp = packageLayout.findViewById(R.id.need_help)
         packageBackArrow = packageLayout.findViewById(R.id.package_back_arrow)
         packageLayoutContainer.addView(packageLayout)
    }

    private fun setClickListeners(){
        pickPackageOption.setOnClickListener {
            viewModel.setPackageLayoutClickListeners((it as MaterialButton).text.toString())
        }

        deliverPackageOption.setOnClickListener {
            viewModel.setPackageLayoutClickListeners((it as MaterialButton).text.toString())
        }

        packageBackArrow.setOnClickListener {
            navController.popBackStack()
        }
    }
}