package com.oceangrsmith.mylockerapplication.ui.packagebox

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.google.android.flexbox.*
import com.google.android.material.button.MaterialButton
import com.oceangrsmith.mylockerapplication.R
import com.oceangrsmith.mylockerapplication.utility.ViewComponent

internal class PackageBoxComponent(
viewModel: PackageBoxViewModel,
val context: Context, private val navController: NavController
) : ViewComponent<PackageBoxViewModel>(viewModel, context) {


    lateinit var backArrow: ImageView
    lateinit var packageBoxTitle: TextView
    lateinit var packageBoxSubTitle: TextView
    lateinit var flexboxLayout: FlexboxLayout
    lateinit var openBoxesButton: MaterialButton
    lateinit var noPendingPackage: TextView

    override fun attachView(container: View) {
        backArrow = container.findViewById(R.id.package_back_arrow)
        packageBoxSubTitle = container.findViewById(R.id.package_box_subTitle)
        packageBoxTitle = container.findViewById(R.id.package_box_title)
        flexboxLayout = container.findViewById(R.id.flexbox_boxes)
        openBoxesButton = container.findViewById(R.id.open_all_boxes)
        noPendingPackage = container.findViewById(R.id.no_pending_package)
        setUpView()
    }

    override fun attachLifecycleOwner(owner: LifecycleOwner) {
    }

   private fun setUpView(){
       setUpRecyclerView()
       when(!viewModel.packages.isNullOrEmpty()){
           true -> {
               openBoxesButton.setText(R.string.open_all_boxes)
           }
           false -> {
               flexboxLayout.visibility = GONE
               noPendingPackage.visibility = VISIBLE
               openBoxesButton.setText(R.string.done)
           }
       }
       openBoxesButton.setText( if(!viewModel.packages.isNullOrEmpty())R.string.open_all_boxes
       else R.string.done)
       setUpClickListeners()
    }

    private fun setUpRecyclerView() {
        flexboxLayout.apply {
                 viewModel.packages?.forEach {
                     val packageBoxView = LayoutInflater.from(context).inflate(R.layout.package_box_layout,null,false)
                     val boxName = packageBoxView.findViewById<TextView>(R.id.box_name)
                     boxName.text = it.name
                     flexboxLayout.addView(packageBoxView)
                 }
             }
    }

    private fun setUpClickListeners(){
        openBoxesButton.setOnClickListener {
            viewModel.handleClickListeners((it as MaterialButton).text.toString())
        }
    }
}