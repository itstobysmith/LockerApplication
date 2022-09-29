package com.oceangrsmith.mylockerapplication.ui.packagebox

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.oceangrsmith.mylockerapplication.R
import com.oceangrsmith.mylockerapplication.model.Package
import com.oceangrsmith.mylockerapplication.utility.DETAILS_OF_PACKAGES_AVAILABLE
import com.oceangrsmith.mylockerapplication.utility.obtainViewModel

class PackageBoxFragment : Fragment() {

    private var packages: ArrayList<Package>? = null
    private lateinit var packageBoxView : View

    private val viewModel by lazy {
        obtainViewModel {
            PackageBoxViewModel(packages)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        packages = arguments?.getParcelableArrayList(DETAILS_OF_PACKAGES_AVAILABLE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        packageBoxView = inflater.inflate(R.layout.fragment_package_box, container, false)
        return packageBoxView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpComponents()
    }

    private fun setUpComponents() {
        PackageBoxComponent(viewModel, requireContext(), findNavController()).apply {
            attachLifecycleOwner(this@PackageBoxFragment)
            attachView(packageBoxView)
        }
    }
}