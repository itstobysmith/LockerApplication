package com.example.envoyapplication.logger

interface IELogger {

    fun printStackTrace(e: Exception) {}

    fun d(msg: String, tag: String, tr: Throwable?): Int = 0

    fun e(msg: String, tag: String, tr: Throwable?): Int = 0
    fun i(msg: String, tag: String, tr: Throwable?): Int = 0
    fun v(msg: String, tag: String, tr: Throwable?): Int = 0
    fun w(msg: String, tag: String, tr: Throwable?): Int = 0

    fun getStackTraceString(tr: Throwable): String = ""
}
