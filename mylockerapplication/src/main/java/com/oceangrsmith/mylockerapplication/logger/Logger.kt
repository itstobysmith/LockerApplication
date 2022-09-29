package com.oceangrsmith.mylockerapplication.logger

import androidx.annotation.VisibleForTesting

object Logger {

    private val releaseLogger = object : IELogger {
    }
    private var logger: IELogger = releaseLogger


    /**
     * Initializes the logging framework with the [logger] provided.
     */
    fun config(logger: IELogger = releaseLogger) {
        Logger.logger = logger
    }

    private val defaultTag: String
        get() = Throwable().stackTrace
            .first { it.className != this::class.java.name }
            .let(Logger::createStackElementTag)


    @VisibleForTesting
    fun createStackElementTag(element: StackTraceElement): String {
        return element.className.substringAfterLast('.')
    }


    /**
     * Prints the details of the [exception].
     */
    fun printStackTrace(exception: Exception) = logger.printStackTrace(exception)

    /**
     * Send a [.DEBUG] log message, [msg] and log the exception [tr] if provided. Also prints a [tag] if provided, or defaults to the [defaultTag].
     */
    fun d(msg: String, tag: String = defaultTag, tr: Throwable? = null): Int =
        logger.d(msg, tag, tr)

    /**
     * Send a [.ERROR] log message, [msg] and log the exception [tr] if provided. Also prints a [tag] if provided, or defaults to the [defaultTag].
     */
    fun e(msg: String, tag: String = defaultTag, tr: Throwable? = null): Int =
        logger.e(msg, tag, tr)

    /**
     * Send a [.INFO] log message, [msg] and log the exception [tr] if provided. Also prints a [tag] if provided, or defaults to the [defaultTag].
     */
    fun i(msg: String, tag: String = defaultTag, tr: Throwable? = null): Int =
        logger.i(msg, tag, tr)

    /**
     * Send a [.VERBOSE] log message, [msg] and log the exception [tr] if provided. Also prints a [tag] if provided, or defaults to the [defaultTag].
     */
    fun v(msg: String, tag: String = defaultTag, tr: Throwable? = null): Int =
        logger.v(msg, tag, tr)

    /**
     * Send a [.WARN] log message, [msg] and log the exception [tr] if provided. Also prints a [tag] if provided, or defaults to the [defaultTag].
     */
    fun w(msg: String, tag: String = defaultTag, tr: Throwable? = null): Int =
        logger.w(msg, tag, tr)

    /**
     * Handy function to get a loggable stack trace from a Throwable, [tr].
     */
    fun getStackTraceString(tr: Throwable): String = logger.getStackTraceString(tr)
}
