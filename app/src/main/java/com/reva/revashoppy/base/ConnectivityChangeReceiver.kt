package com.reva.revashoppy.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

class ConnectivityChangeReceiver(private val listener: OnConnectivityChangedListener) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetInfo = connectivityManager.activeNetworkInfo
        val isConnected = activeNetInfo != null && activeNetInfo.isConnectedOrConnecting
        listener.onConnectivityChanged(isConnected)
    }

    interface OnConnectivityChangedListener {
        fun onConnectivityChanged(isConnected: Boolean)
    }
}
