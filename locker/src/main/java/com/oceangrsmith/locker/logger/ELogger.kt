package com.oceangrsmith.locker.logger

import android.util.Log
import com.example.envoyapplication.logger.IELogger

class ELogger : IELogger {
    override fun printStackTrace(e: Exception) = e.printStackTrace(System.err)

    override fun d(msg: String, tag: String, tr: Throwable?): Int = Log.d(tag, msg, tr)

    override fun e(msg: String, tag: String, tr: Throwable?): Int = Log.e(tag, msg, tr)

    override fun i(msg: String, tag: String, tr: Throwable?): Int = Log.i(tag, msg, tr)

    override fun v(msg: String, tag: String, tr: Throwable?): Int = Log.v(tag, msg, tr)

    override fun w(msg: String, tag: String, tr: Throwable?): Int = Log.w(tag, msg, tr)

    override fun getStackTraceString(tr: Throwable): String = Log.getStackTraceString(tr)
}
