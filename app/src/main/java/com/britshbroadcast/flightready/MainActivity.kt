package com.britshbroadcast.flightready

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.britshbroadcast.flightready.databinding.ActivityMainBinding
import com.britshbroadcast.flightready.receiver.AirPlaneModeReceiver
import com.britshbroadcast.flightready.view.FlightItemAdapter

class MainActivity : AppCompatActivity(), AirPlaneModeReceiver.AirPlaneModeDelegate {

    private val airPlaneModeReceiver = AirPlaneModeReceiver(this)

    private lateinit var binding: ActivityMainBinding

    private lateinit var slideInAnimation: Animation
    private lateinit var slideOutAnimation: Animation

    private var itemList = mutableListOf("food", "headphones","game","book")
    private var adapter = FlightItemAdapter(itemList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        slideInAnimation = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)
        slideOutAnimation = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right)


        setContentView(binding.root)
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView.adapter = adapter
        //Registering the receiver
        registerReceiver(airPlaneModeReceiver, IntentFilter("android.intent.action.AIRPLANE_MODE"))
        checkAirPlaneMode()

        binding.airplaneModeButton.setOnClickListener {

            startActivity(Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS))

        }


    }

    private fun checkAirPlaneMode() {

        if(Settings.System.getInt(contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) != 0){
            hideOverlay()
        }else
           showOverlay()

    }



    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(airPlaneModeReceiver)
    }

    override fun showOverlay() {
        binding.overlayView.visibility = View.VISIBLE
        binding.overlayView.animation = slideInAnimation

    }

    override fun hideOverlay() {
        binding.overlayView.visibility = View.GONE
        binding.overlayView.animation = slideOutAnimation

    }
}