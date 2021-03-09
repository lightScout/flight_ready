package com.britshbroadcast.flightready.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import com.britshbroadcast.flightready.util.Logger.Companion.logError

class AirPlaneModeReceiver(val airPlaneModeDelegate: AirPlaneModeDelegate): BroadcastReceiver() {

     interface AirPlaneModeDelegate{
        fun showOverlay()
        fun hideOverlay()
    }

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action

        if(action == "android.intent.action.AIRPLANE_MODE"){

            val on: Boolean = getModeState(context)
            if(!on){
                airPlaneModeDelegate.showOverlay()
            }else
                airPlaneModeDelegate.hideOverlay()

        }else{
            logError("Not airplane mode")
        }

    }

    private fun getModeState(context: Context): Boolean = Settings.System.getInt(context.contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) != 0
}