package com.devkaybee.smartparking.ui.admin.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.devkaybee.smartparking.R
import com.devkaybee.smartparking.data.AddSlots
import com.devkaybee.smartparking.databinding.FragmentStatusBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class StatusFragment : Fragment(R.layout.fragment_status) {

    private lateinit var statusBinding: FragmentStatusBinding
    private lateinit var slotsDatabase: DatabaseReference


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statusBinding = FragmentStatusBinding.bind(view)

        statusBinding.fabMain.setOnClickListener{
            statusBinding.addSlotsFab.visibility = View.VISIBLE
            statusBinding.txtAdd.visibility = View.VISIBLE
        }


        statusBinding.addSlotsFab.setOnClickListener {

            statusBinding.addSlotsLayout.visibility = View.VISIBLE
            statusBinding.addSlotsFab.visibility = View.GONE
            statusBinding.txtAdd.visibility = View.GONE

        }

        statusBinding.addSlotsBtn.setOnClickListener {
            addSlots()
        }
        statusBinding.cancelBtn.setOnClickListener {  }

    }

    private fun addSlots(){
        var parkSlot = statusBinding.edtxtParkSlote.text.toString()
        var parkLocation = statusBinding.edtxtParkLocation.text.toString()
        var parkCapacity = statusBinding.edtxtMaxCapacity.text.toString()

        val slots = AddSlots(parkSlot, parkLocation,parkCapacity)
        slotsDatabase = FirebaseDatabase.getInstance().getReference("Slots")
        slotsDatabase.child(parkSlot).setValue(slots).addOnSuccessListener {

            Toast.makeText(requireContext(), "Slot Added Successfully", Toast.LENGTH_SHORT).show()
            statusBinding.addSlotsLayout.visibility = View.VISIBLE
            statusBinding.addSlotsFab.visibility = View.GONE
            statusBinding.txtAdd.visibility = View.GONE
        }
    }
}