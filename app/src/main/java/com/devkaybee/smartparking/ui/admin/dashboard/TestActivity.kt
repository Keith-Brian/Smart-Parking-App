package com.devkaybee.smartparking.ui.admin.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.devkaybee.smartparking.R
import com.devkaybee.smartparking.data.ParkingSlots
import com.devkaybee.smartparking.databinding.ActivityTestBinding
import com.devkaybee.smartparking.ui.admin.adapter.ParkListAdapter
import com.google.android.material.search.SearchView

class TestActivity : AppCompatActivity() {
    private lateinit var testBinding: ActivityTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        testBinding = ActivityTestBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(testBinding.root)



        var parkSlots = mutableListOf(
            ParkingSlots("GPO", "Nairobi, CBD", "6"),
            ParkingSlots("Garden City Mall", "Thika Road", "8"),
            ParkingSlots("China Square", "Thika Road", "10"),
            ParkingSlots("Apple Cross Road", "Lavington", "8"),
            ParkingSlots("GPO", "Nairobi, CBD", "6"),
            ParkingSlots("Garden City Mall", "Thika Road", "8"),
            ParkingSlots("China Square", "Thika Road", "10"),
            ParkingSlots("Apple Cross Road", "Lavington", "8")
        )


        val itemAdapter = ParkListAdapter(parkSlots)
        testBinding.rcvParkingSlots.layoutManager = LinearLayoutManager(this)
        testBinding.rcvParkingSlots.adapter = itemAdapter

    }
}