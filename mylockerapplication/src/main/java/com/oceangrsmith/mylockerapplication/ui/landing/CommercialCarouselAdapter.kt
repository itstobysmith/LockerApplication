package com.oceangrsmith.mylockerapplication.ui.landing

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oceangrsmith.mylockerapplication.R
import com.oceangrsmith.mylockerapplication.model.*

class CommercialCarouselAdapter(val context: Context, val viewModel: LandingViewModel) : RecyclerView.Adapter<BaseViewHolder>() {

    private var commercials: List<CommercialData> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            1 -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.image_commercials_layout_item, parent, false)
                ImageViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.video_commercials_layout_item, parent, false)
                VideoViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is ImageViewHolder -> holder.bind(commercials[position].url, viewModel)
            is VideoViewHolder -> holder.bind(commercials[position].url)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (commercials[position].state) {
            CommercialState.IMAGE -> 1
            else -> 2
        }
    }

    override fun getItemCount(): Int = commercials.size

    fun setData(data: List<CommercialData>) {
        commercials = data
    }
}