package com.oceangrsmith.mylockerapplication.utility

import android.content.Context
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel

internal abstract class ViewComponent<T : ViewModel>(val viewModel: T, context: Context) {
    abstract fun attachView(container: View)
    abstract fun attachLifecycleOwner(owner: LifecycleOwner)
}
