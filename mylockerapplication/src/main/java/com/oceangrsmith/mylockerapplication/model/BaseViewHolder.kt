package com.oceangrsmith.mylockerapplication.model

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.oceangrsmith.mylockerapplication.R
import com.oceangrsmith.mylockerapplication.ui.landing.LandingViewModel


sealed class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view)

class ImageViewHolder(val view: View) : BaseViewHolder(view) {
    var imageViewBackground: ImageView = view.findViewById(R.id.commercials_image)
    fun bind( videoUrl: String, viewModel: LandingViewModel){
          viewModel.loadImage(ImageDownloaderOptions(videoUrl),imageViewBackground, view.context)
    }
}

class VideoViewHolder(val view: View) : BaseViewHolder(view) {

    private val exoPlayerView:  SimpleExoPlayerView = view.findViewById(R.id.simple_player_view)
    fun bind(videoUrl: String) {
        val bandwidthMeter = DefaultBandwidthMeter()


        // track selector is used to navigate between
        // video using a default seekbar.
        val trackSelector: TrackSelector =
            DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandwidthMeter))

        // we are adding our track selector to exoplayer.

        // we are adding our track selector to exoplayer.
        val exoPlayer = ExoPlayerFactory.newSimpleInstance(view.context, trackSelector)

        // we are parsing a video url
        // and parsing its video uri.

        // we are parsing a video url
        // and parsing its video uri.
        val videouri: Uri = Uri.parse(videoUrl)

        // we are creating a variable for datasource factory
        // and setting its user agent as 'exoplayer_view'

        // we are creating a variable for datasource factory
        // and setting its user agent as 'exoplayer_view'
        val dataSourceFactory = DefaultHttpDataSourceFactory("exoplayer_video")

        // we are creating a variable for extractor factory
        // and setting it to default extractor factory.

        // we are creating a variable for extractor factory
        // and setting it to default extractor factory.
        val extractorsFactory: ExtractorsFactory = DefaultExtractorsFactory()

        // we are creating a media source with above variables
        // and passing our event handler as null,

        // we are creating a media source with above variables
        // and passing our event handler as null,
        val mediaSource: MediaSource =
            ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory, null, null)

        // inside our exoplayer view
        // we are setting our player

        // inside our exoplayer view
        // we are setting our player
        exoPlayerView.player = exoPlayer

        // we are preparing our exoplayer
        // with media source.

        // we are preparing our exoplayer
        // with media source.
        exoPlayer.prepare(mediaSource)

        // we are setting our exoplayer
        // when it is ready.

        // we are setting our exoplayer
        // when it is ready.
        exoPlayer.playWhenReady = true
    }
}
