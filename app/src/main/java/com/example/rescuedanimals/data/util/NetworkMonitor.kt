package com.example.rescuedanimals.data.util

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject


interface NetworkMonitor {
    fun isConnected():Boolean
}

class LiveNetworkMonitor @Inject constructor(
    private val context: Context
):NetworkMonitor {
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun isConnected(): Boolean {
        val network = connectivityManager.activeNetwork
        return network != null
    }
}