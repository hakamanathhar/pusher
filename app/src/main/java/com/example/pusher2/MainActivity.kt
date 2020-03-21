package com.example.pusher2

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import com.pusher.client.channel.SubscriptionEventListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var adapter:BookingAdapter
    private val bookingList = ArrayList<String>()
    companion object {
        const val SERVER_URL = "http://NODE_JS_SERVER_ENDPOINT"
        const val PUSHER_API_KEY = "9d8144c80f8a8cd6f8f1"
        const val PUSHER_CLUSTER = "ap1"
        const val CLICK_CHANNEL = "click-channel"
        const val CLICK_EVENT = "click-event"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        BtnSave.setOnClickListener(this)
        bookingList.add("a")
        adapter = BookingAdapter(
            this,
            bookingList){

        }
        rv_booking.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_booking.adapter = adapter

        pusher()
        subscribeToChannel()
    }

    private fun pusher() {
        val options = PusherOptions()
        options.setCluster(PUSHER_CLUSTER)
        val pusher = Pusher(PUSHER_API_KEY,options)
        val channel = pusher.subscribe("records")

        channel.bind("App\\Events\\UpdateCreated", SubscriptionEventListener { channelName, eventName, data ->
            runOnUiThread {
                val resultJson = JSONObject(data)
                Log.d("__DBG_CHANNEL",channelName)
                Log.d("__DBG_EVENT_NAME",eventName)
                val nilai = resultJson.getString("update")
                bookingList.add(nilai)
                adapter.notifyDataSetChanged()
            }

        });
        // connect to the Pusher API
        pusher.connect();
    }

    private fun subscribeToChannel() {

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
//            R.id.BtnSave -> addEmployee.text = edtName.text.toString()
        }
    }

    private fun addEmployees() {



    }
}
