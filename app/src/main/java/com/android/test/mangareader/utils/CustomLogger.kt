package com.android.test.mangareader.utils

import android.util.Log
import com.android.test.mangareader.BuildConfig

class CustomLogger {
    companion object {
        fun log(message: String) {
            if (BuildConfig.DEBUG) {
                Log.d(CustomLogger::class.simpleName, message)
            }
        }

        fun log(e: Exception) {
            if (BuildConfig.DEBUG) {
                Log.d(CustomLogger::class.simpleName, e.message.toString())
            }
        }
    }
}