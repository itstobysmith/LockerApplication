package com.oceangrsmith.mylockerapplication.repository

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.oceangrsmith.mylockerapplication.model.ImageDownloaderOptions

interface ImageLoaderRepository {

    fun displayImage(
        imageOptions: ImageDownloaderOptions,
        imageView: ImageView,
        context: Context
    )

    suspend fun getImageBitmap(imageOptions: ImageDownloaderOptions): Bitmap?

    companion object {
        fun getImageLoaderRepository(context: Context) : ImageLoaderRepository =
            ImageLoaderRepositoryImpl(context)
    }
}
