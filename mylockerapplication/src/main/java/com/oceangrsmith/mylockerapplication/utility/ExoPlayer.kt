package com.oceangrsmith.mylockerapplication.utility

import android.content.Context
import com.google.android.exoplayer2.ExoPlayer

class ExoPlayer(val context: Context) {

    var exoplayerInstance : ExoPlayer = ExoPlayer.Builder(context).build()

}