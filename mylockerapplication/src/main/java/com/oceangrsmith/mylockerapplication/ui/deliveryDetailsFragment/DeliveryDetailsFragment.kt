package com.oceangrsmith.mylockerapplication.ui.deliveryDetailsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.oceangrsmith.mylockerapplication.R
import com.oceangrsmith.mylockerapplication.utility.obtainViewModel

class DeliveryDetailsFragment : Fragment() {

    lateinit var deliveryDetailsView: View

    private val viewModel by lazy {
        obtainViewModel {
            DeliveryDetailsViewModel()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        deliveryDetailsView = inflater.inflate(R.layout.fragment_delivery_details, container, false)
        return deliveryDetailsView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpComponents()
    }

    private fun setUpComponents(){
        DeliveryDetailsComponent(viewModel, requireContext(),findNavController()).apply {
            attachLifecycleOwner(this@DeliveryDetailsFragment)
            attachView(deliveryDetailsView)
        }
    }
}