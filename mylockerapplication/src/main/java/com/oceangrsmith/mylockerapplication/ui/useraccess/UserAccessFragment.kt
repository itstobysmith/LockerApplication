package com.oceangrsmith.mylockerapplication.ui.useraccess

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.oceangrsmith.mylockerapplication.R
import com.oceangrsmith.mylockerapplication.repository.PinRepository
import com.oceangrsmith.mylockerapplication.utility.PIN_NAVIGATION_KEY
import com.oceangrsmith.mylockerapplication.utility.obtainViewModel


class UserAccessFragment : Fragment() {

    lateinit var pinView: View
    lateinit var pinNavigationKey: String

    private val viewModel by lazy {
        obtainViewModel {
            UserAccessViewModel(
                initialNavigationKey = pinNavigationKey,
                pinRepository = PinRepository.getPinRepository(requireContext())
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pinNavigationKey = arguments?.getString(PIN_NAVIGATION_KEY).orEmpty()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        pinView = inflater.inflate(R.layout.fragment_pin, container, false)
        return pinView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpComponents()
    }

    private fun setUpComponents() {
        UserAccessComponent(viewModel, requireContext(), findNavController()).apply {
            attachLifecycleOwner(this@UserAccessFragment)
            attachView(pinView)
        }
    }
}