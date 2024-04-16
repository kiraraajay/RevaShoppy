package com.reva.revashoppy.common

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.Locale

class LocationManagerComman(private val context: Context) {

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1001

        var latitude1=0.0
        var longitude1=0.0
        var address1 =""
    }

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    fun checkLocationAndNavigate() {
        if (hasLocationPermission()) {
            checkLocationEnabled()
        } else {
            requestLocationPermission()
        }
    }

    private fun hasLocationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    private fun checkLocationEnabled() {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // GPS is not enabled, prompt the user to enable it
            Toast.makeText(
                context,
                "Please enable GPS to get your location",
                Toast.LENGTH_SHORT
            ).show()
            // Open location settings to allow the user to enable GPS
            context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        } else {
            // GPS is enabled, proceed to request location updates
            requestLocationUpdates()
        }
    }

    private fun requestLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                location?.let {
                    // Latitude and longitude are available
                    val latitude = it.latitude
                    val longitude = it.longitude
                    // Proceed with your logic, like geocoding
                    geocodeLocation(latitude, longitude)
                } ?: run {
                    // Location is null, show toast message or handle accordingly
                    //Toast.makeText(context, "Unable to retrieve location", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception ->
                // Handle failure to get location updates
                Toast.makeText(
                    context,
                    "Error getting location: ${exception.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun geocodeLocation(latitude: Double, longitude: Double) {
        val geocoder = Geocoder(context, Locale.getDefault())
        try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            // Process the addresses


            val address: String = addresses!![0].getAddressLine(0)


            latitude1 =latitude
            longitude1 =longitude
            address1 = address

        } catch (e: Exception) {
            // Handle exceptions, such as IOException
            Toast.makeText(
                context,
                "Error geocoding location: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
