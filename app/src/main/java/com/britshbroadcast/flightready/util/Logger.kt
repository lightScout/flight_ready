package com.britshbroadcast.flightready.util

import android.util.Log

class Logger {



    companion object{

        const val TAG = "TAG_J"

        fun logDebug(message: String){
            Log.d(TAG, message)

        }

        fun logError(message: String){
            Log.e(TAG, message, )
        }

    }
}