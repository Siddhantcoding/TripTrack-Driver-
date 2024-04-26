// app/src/main/java/com/example/triptrackdriver/screen/mapscreen/MapScreen.kt
package com.example.triptrackdriver.screen.mapscreen

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.triptrackdriver.R
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.maps.MapView
import com.mapbox.maps.MapboxMap
import com.mapbox.maps.Style
import com.mapbox.maps.extension.observable.model.RenderMode
import com.mapbox.maps.plugin.Plugin

abstract class MapScreen : AppCompatActivity(), OnMapReadyCallback, PermissionsListener {

    private lateinit var map: MapboxMap
    private lateinit var permissionsManager: PermissionsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Plugin.Mapbox.getInstance(this, "YOUR_MAPBOX_ACCESS_TOKEN")
        setContentView(R.layout.activity_map_screen)

        val mapView = findViewById<MapView>(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onMapReady(mapboxMap: MapboxMap) {
        map = mapboxMap
        map.setStyle(Style.MAPBOX_STREETS) {
            enableLocationComponent(it)
        }
    }

    private fun enableLocationComponent(style: Style) {
        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            val locationComponent = map.locationComponent
            locationComponent.activateLocationComponent(
                LocationComponentActivationOptions.builder(this, style).build()
            )
            locationComponent.isLocationComponentEnabled = true
            locationComponent.cameraMode = CameraMode.TRACKING
            locationComponent.renderMode = RenderMode.COMPASS
        } else {
            permissionsManager = PermissionsManager(this)
            permissionsManager.requestLocationPermissions(this)
        }
    }

    override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {
        // Present a toast or a dialog explaining why the app needs the location permission
    }

    override fun onPermissionResult(granted: Boolean) {
        if (granted) {
            map.getStyle { style -> enableLocationComponent(style) }
        } else {
            // Handle the case where the user denied the location permission
        }
    }
}