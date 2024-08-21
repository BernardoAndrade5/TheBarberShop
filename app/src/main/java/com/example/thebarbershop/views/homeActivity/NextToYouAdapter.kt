package com.example.thebarbershop.views.homeActivity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thebarbershop.R
import com.example.thebarbershop.databinding.AppointmentsListItemBinding
import com.example.thebarbershop.databinding.NextToYouRvLayoutItemBinding
import com.example.thebarbershop.models.Appointment
import com.example.thebarbershop.models.Business

class NextToYouAdapter(
    private val context: Context,
    private val nextToYouBusinessList: MutableList<Business>
) : RecyclerView.Adapter<NextToYouAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: NextToYouRvLayoutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val businessName: TextView = binding.itemTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextToYouAdapter.ViewHolder {
        val binding = NextToYouRvLayoutItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = nextToYouBusinessList[position]
        holder.businessName.text = data.name

        // Load the image with Glide and apply a circular transformation
        /*Glide.with(context)
            .load(data.imageUrl)
            .circleCrop() // Apply circular cropping
            .placeholder(R.drawable.ic_placeholder) // Optional placeholder image
            .into(holder.itemImage)*/
    }

    override fun getItemCount(): Int {
        return nextToYouBusinessList.size
    }

    fun updateData(newBusinessList: List<Business>) {
        nextToYouBusinessList.clear()
        nextToYouBusinessList.addAll(newBusinessList)
        notifyDataSetChanged()
    }

}
