package com.oceangrsmith.mylockerapplication.ui.landing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.oceangrsmith.mylockerapplication.R
import com.oceangrsmith.mylockerapplication.repository.ImageLoaderRepository
import com.oceangrsmith.mylockerapplication.utility.obtainViewModel

class LandingFragment : Fragment() {

    private lateinit var entranceView : View

    private val viewModel by lazy {
        obtainViewModel {
            LandingViewModel(ImageLoaderRepository.getImageLoaderRepository(requireContext()))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        entranceView = inflater.inflate(R.layout.fragment_entrance, container, false)
        return entranceView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpComponents()
    }

    private fun setUpComponents(){
        LandingComponent(viewModel,requireContext(),findNavController()).apply {
            attachLifecycleOwner(this@LandingFragment)
            attachView(entranceView)
        }
    }
}