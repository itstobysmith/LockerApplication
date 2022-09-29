package com.oceangrsmith.mylockerapplication.ui.deliveryDetailsFragment

import android.content.Context
import android.view.View
import android.widget.*
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.button.MaterialButton
import com.oceangrsmith.mylockerapplication.R
import com.oceangrsmith.mylockerapplication.utility.ViewComponent

internal class DeliveryDetailsComponent(viewModel: DeliveryDetailsViewModel, val context: Context, val navController: NavController)
    : ViewComponent<DeliveryDetailsViewModel>(viewModel, context) {

    lateinit var deliveryLockerLogo : ImageView
    lateinit var backArrow : ImageView
    lateinit var deliveryDetailsTitle : TextView
    lateinit var deliveryDetailSubtitle: TextView
    lateinit var packageSizeDropdown: AutoCompleteTextView
    lateinit var unitNumberDropdown: AutoCompleteTextView
    lateinit var courierDropdown: AutoCompleteTextView
    lateinit var otherCouriers : EditText
    lateinit var continueButton: MaterialButton

    override fun attachView(container: View) {
        deliveryLockerLogo = container.findViewById(R.id.delivery_locker_logo)
        backArrow = container.findViewById(R.id.delivery_details_back_arrow)
        deliveryDetailsTitle = container.findViewById(R.id.delivery_details_title)
        deliveryDetailSubtitle = container.findViewById(R.id.delivery_details_subtitle)
        packageSizeDropdown = container.findViewById(R.id.packages_size_autoComplete)
        unitNumberDropdown = container.findViewById(R.id.unit_number_autoComplete)
        courierDropdown = container.findViewById(R.id.courier_autoComplete)
        otherCouriers = container.findViewById(R.id.other_courier)
        continueButton = container.findViewById(R.id.continue_button)
        setUpViews()
    }

    override fun attachLifecycleOwner(owner: LifecycleOwner) {
        viewModel.popUpData.observe(owner,  {

        })

        viewModel.navigationData.observe(owner, {
            navController.navigate(it.id,it.bundle)
        })

    }

    private fun setUpViews(){
        deliveryDetailsTitle.text = context.getString(R.string.delivery_details_title)
        deliveryDetailSubtitle.text = context.getString(R.string.delivery_details_subtitle)

        //set up packages
        val packageSizes = context.resources.getStringArray(R.array.packages_size)
        val arrayAdapter = ArrayAdapter(context,R.layout.dropdown_item,packageSizes)
        packageSizeDropdown.setAdapter(arrayAdapter)


        //set up unit numbers
        val unitNumbers = context.resources.getStringArray(R.array.unit_number)
        val unitNumberAdapter = ArrayAdapter(context,R.layout.dropdown_item,unitNumbers)
        unitNumberDropdown.setAdapter(unitNumberAdapter)

        //set up couriers
        val couriers = context.resources.getStringArray(R.array.courier_services)
        val courierAdapter = ArrayAdapter(context,R.layout.dropdown_item,couriers)
        courierDropdown.setAdapter(courierAdapter)
        courierDropdown.setOnItemClickListener { _, _, i, _ ->
            if(couriers[i] == "Others"){
                otherCouriers.visibility = View.VISIBLE
            } else {
                otherCouriers.visibility = View.GONE
            }
        }

        backArrow.setOnClickListener {
            it?.findNavController()?.popBackStack(R.id.pinFragment,true)
        }

        continueButton.setOnClickListener {
            viewModel.navigateToOpenLockerScreen()
        }
    }


}