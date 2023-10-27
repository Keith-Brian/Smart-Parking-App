package com.devkaybee.smartparking.ui.admin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devkaybee.smartparking.R
import com.devkaybee.smartparking.R.layout.admin_itemparkslots
import com.devkaybee.smartparking.data.ParkingSlots


class ParkListAdapter(var parkingList: List<ParkingSlots>): RecyclerView.Adapter<ParkListAdapter.ParkListViewHolder>(){

    inner class  ParkListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var parkTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var parkLocation: TextView = itemView.findViewById(R.id.tvParkLocation)
        var parkedVehicles: TextView = itemView.findViewById(R.id.tvParkedVehicleNumbers)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.admin_itemparkslots, parent,false)
        return  ParkListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParkListViewHolder, position: Int) {

        holder.apply {
            parkTitle.text = parkingList[position].slotName
            parkedVehicles.text = parkingList[position].parkedVehicles
            parkLocation.text = parkingList[position].location
        }
    }
    override fun getItemCount(): Int {
        return  parkingList.size
    }
}