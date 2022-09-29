package com.oceangrsmith.mylockerapplication.repository

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.oceangrsmith.mylockerapplication.model.ImageDownloaderOptions
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ImageLoaderRepositoryImpl(private val context: Context) : ImageLoaderRepository {

    override fun displayImage(imageOptions: ImageDownloaderOptions, imageView: ImageView, context: Context) {
        Glide.with(context)
            .load(imageOptions.url)
            .placeholder(imageOptions.placeholder)
            .into(imageView)
    }

    override suspend fun getImageBitmap(imageOptions: ImageDownloaderOptions): Bitmap? {
        return suspendCoroutine { operation ->
            val listener = object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    operation.resume(null)
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    operation.resume(resource)
                    return true
                }

            }
            Glide.with(context.applicationContext).asBitmap().load(Uri.parse(imageOptions.url))
                .listener(listener)
                .submit()
        }
    }
}
