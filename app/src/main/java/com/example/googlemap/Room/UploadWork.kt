package com.example.googlemap.Room

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

@SuppressLint("StaticFieldLeak")
lateinit var fusedLocationProviderClient: FusedLocationProviderClient
lateinit var appDatabase: AppDatabase
class UploadWork(var context: Context,workerParameters: WorkerParameters):
                Worker(context,workerParameters){
    private val TAG = "UploadWork"
    override fun doWork(): Result {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

        setLocation()

        return Result.success()
    }

    private fun setLocation() {
        appDatabase = AppDatabase.getInstance(applicationContext)



        val locationResult = fusedLocationProviderClient.lastLocation
        locationResult.addOnCompleteListener { task ->
            val lastKnownLocation = task.result
            val location = Location(lastKnownLocation.latitude,lastKnownLocation.longitude)
            appDatabase.locationDao().insertLocation(location)
            Log.d(TAG, "setLocation: ${lastKnownLocation.latitude},${lastKnownLocation.longitude}")
            Toast.makeText(context, "${lastKnownLocation.latitude},${lastKnownLocation.longitude}", Toast.LENGTH_LONG).show()
        }
    }
}