package com.devkaybee.smartparking.ui.admin.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.devkaybee.smartparking.data.AddSlots
import com.devkaybee.smartparking.data.api.Operability
import com.devkaybee.smartparking.data.mqtt.MattViewModel
import com.devkaybee.smartparking.databinding.ActivityTestBinding
import com.devkaybee.smartparking.ui.admin.adapter.ParkListAdapter
import com.devkaybee.smartparking.ui.driver.dashboard.BookingActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TestActivity : AppCompatActivity() {
    private lateinit var testBinding: ActivityTestBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var parkingArrayList: ArrayList<AddSlots>

    private lateinit var mqttViewModel: MattViewModel

    private lateinit var operability: Operability
    override fun onCreate(savedInstanceState: Bundle?) {
        testBinding = ActivityTestBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(testBinding.root)

        //connecting to the server option
         mqttViewModel = ViewModelProvider(this).get(MattViewModel::class.java)
         mqttViewModel.connect(this)

        operability = Operability()

        testBinding.rcvParkingSlots.layoutManager = LinearLayoutManager(this)
        testBinding.rcvParkingSlots.setHasFixedSize(true)
        parkingArrayList = arrayListOf<AddSlots>()
        getData();


    }

    private fun getData() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Slots")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        var slots = userSnapshot.getValue(AddSlots::class.java)

                        parkingArrayList.add(slots!!)
                    }

                    testBinding.rcvParkingSlots.adapter = ParkListAdapter(parkingArrayList)



                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}