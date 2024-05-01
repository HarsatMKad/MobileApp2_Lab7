package com.example.yandexmaplab

import android.app.SearchManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

var key = "7e992415-c2a9-4e6e-95c6-179221ec4724"

private lateinit var mapView: MapView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapKitFactory.setApiKey(key)
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.mapview)
        val mapKit: MapKit = MapKitFactory.getInstance()
        val probki = mapKit.createTrafficLayer(mapView.mapWindow)

        val btnSity = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val btnPochta = findViewById<FloatingActionButton>(R.id.floatingActionButtonPochta)
        val togglebtn = findViewById<ToggleButton>(R.id.toggleButton)

        btnSity.setOnClickListener {
            mapView.map.move(CameraPosition(Point(findKemerovo()[0], findKemerovo()[1]), 11.0f, 0.0f, 0.0f))
        }

        btnPochta.setOnClickListener {
            mapView.map.move(CameraPosition(Point(findGlavPochta()[0], findGlavPochta()[1]), 18.0f, 0.0f, 0.0f))
        }

        togglebtn.setOnClickListener {
            if(togglebtn.isChecked){
                probki.isTrafficVisible = true
            } else {
                probki.isTrafficVisible = false
            }
        }
    }

    fun findKemerovo(): DoubleArray{
        val kemerovo = doubleArrayOf(55.3333, 86.0833)
        return kemerovo
    }

    fun findGlavPochta(): DoubleArray{
        val kemerovo = doubleArrayOf(55.354993, 86.0858053)
        return kemerovo
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
}