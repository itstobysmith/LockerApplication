package com.oceangrsmith.locker.utility

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * Fragment extension function for obtaining an [instance] of the view model through ViewModelProvider
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified V : ViewModel> Fragment.obtainViewModel(crossinline instance: () -> V): V {
    val factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return instance() as T
        }
    }
    return ViewModelProvider(this, factory).get(V::class.java)
}
