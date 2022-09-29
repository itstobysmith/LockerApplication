package com.oceangrsmith.mylockerapplication.model


/**
 * A class used to hold the options for image downloader
 *
 * @param [url] the url of the image
 */
data class ImageDownloaderOptions(
    val url: String,
    val placeholder: Int = 0
)
