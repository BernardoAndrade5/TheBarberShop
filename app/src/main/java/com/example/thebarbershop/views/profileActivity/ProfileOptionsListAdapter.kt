package com.example.thebarbershop.views.profileActivity

import android.provider.ContactsContract.Profile
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thebarbershop.databinding.AppointmentsListItemBinding
import com.example.thebarbershop.databinding.ProfileRvListItemBinding
import com.example.thebarbershop.models.Appointment
import com.example.thebarbershop.models.ProfileOption

class ProfileOptionsListAdapter (private val profileOptionsList: List<ProfileOption>):
    RecyclerView.Adapter<ProfileOptionsListAdapter.ViewHolder>() {

        inner class ViewHolder(private val binding: ProfileRvListItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val buttonIcon: ImageView = binding.imageView
            val buttonName: TextView = binding.buttonName
            val buttonDescription: TextView = binding.buttonDescription
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = ProfileRvListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val profileOptions = profileOptionsList[position]
            holder.buttonName.text = profileOptions.name
            holder.buttonDescription.text = profileOptions.description
            val iconName = profileOptions.icon
            val resourceId = holder.itemView.context.resources.getIdentifier(
                iconName,
                "drawable",
                holder.itemView.context.packageName
            )

            holder.buttonIcon.setImageResource(resourceId)
        }

        override fun getItemCount(): Int {
            return profileOptionsList.size
        }
}