package com.oceangrsmith.mylockerapplication.ui.openlocker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.oceangrsmith.mylockerapplication.R
import com.oceangrsmith.mylockerapplication.model.NavigationData
import com.oceangrsmith.mylockerapplication.ui.deliveryDetailsFragment.DeliveryDetailsComponent
import com.oceangrsmith.mylockerapplication.ui.deliveryDetailsFragment.DeliveryDetailsViewModel
import com.oceangrsmith.mylockerapplication.utility.obtainViewModel

class OpenLockerFragment : Fragment() {

    lateinit var openLockerView: View

    lateinit var lockerName : String

    private val viewModel by lazy {
        obtainViewModel {
            OpenLockerViewModel(lockerName)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lockerName = arguments?.getString(LOCKER).orEmpty()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        openLockerView =  inflater.inflate(R.layout.fragment_open_locker, container, false)
        return openLockerView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpComponents()
    }

    private fun setUpComponents(){
        OpenLockerComponent(viewModel, requireContext(),findNavController()).apply {
            attachLifecycleOwner(this@OpenLockerFragment)
            attachView(openLockerView)
        }
    }

    fun createNavigationData(lockerName : String) : NavigationData{
        return NavigationData(
            R.id.action_DeliveryFragment_to_openlockerFragment,
            Bundle().apply {
                this.putString(LOCKER,lockerName)
            }
        )
    }

    companion object {
        const val LOCKER = "locker"
    }

}