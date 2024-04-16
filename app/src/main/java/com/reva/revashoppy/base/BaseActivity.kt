package com.reva.revashoppy.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.reva.revashoppy.interfaces.CheckInternet

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), CheckInternet {

    lateinit var binding: VB
    abstract fun getInjectViewBinding(): VB
    open fun setUpViewModelObserver() {}
    private var progressBar: ProgressBar? = null
    private val mSnackBar: Snackbar? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getInjectViewBinding()
        setContentView(binding.root)

        internetFun()
    }

     private fun internetFun(){
      val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

      val networkCallback = object : ConnectivityManager.NetworkCallback() {
          // network is available for use
          override fun onAvailable(network: Network) {
              super.onAvailable(network)
            //  Toast.makeText(this@BaseActivity,"onAvailable",Toast.LENGTH_SHORT).show()
              isInternetCheck(true)
          }

          // Network capabilities have changed for the network
          override fun onCapabilitiesChanged(
              network: Network,
              networkCapabilities: NetworkCapabilities
          ) {
              super.onCapabilitiesChanged(network, networkCapabilities)
              val unmetered = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
           //   Toast.makeText(this@BaseActivity,"onCapabilitiesChanged",Toast.LENGTH_SHORT).show()
          }

          // lost network connection
          override fun onLost(network: Network) {
              super.onLost(network)
              isInternetCheck(false)
             // Toast.makeText(this@BaseActivity,"onLost",Toast.LENGTH_SHORT).show()
          }
      }

      // Register the network callback
      connectivityManager.registerNetworkCallback(NetworkRequest.Builder().build(), networkCallback)
  }


}