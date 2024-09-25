package com.example.thebarbershop.views.profileActivity

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.thebarbershop.databinding.ProfileRvListItemBinding
import com.example.thebarbershop.models.ProfileOption
import com.example.thebarbershop.views.profileActivity.profileOptions.changePasswordActivity.ChangePasswordActivity
import com.example.thebarbershop.views.profileActivity.profileOptions.myaccountActivity.MyAccountActivity
import com.example.thebarbershop.views.profileActivity.profileOptions.myaddressActivity.MyAddressActivity
import com.example.thebarbershop.views.profileActivity.profileOptions.myappointmentsActivity.MyAppointmentsActivity
import com.example.thebarbershop.views.profileActivity.profileOptions.myexpensesActivity.MyExpensesActivity
import com.example.thebarbershop.views.profileActivity.profileOptions.myfavoritesActivity.MyFavoritesActivity
import com.example.thebarbershop.views.profileActivity.profileOptions.mypackagesActivity.MyPackagesActivity
import com.example.thebarbershop.views.profileActivity.profileOptions.mysubscriptionsActivity.MySubscriptionsActivity

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
            holder.buttonName.setOnClickListener {
                when (position) {
                    0 -> {//minha conta
                        val context = holder.itemView.context
                        val intent =Intent(context, MyAccountActivity::class.java)
                        startActivity(context, intent, null);
                    }
                    2 -> {//o meu endereço
                        val context = holder.itemView.context
                        val intent =Intent(context, MyAddressActivity::class.java)
                        startActivity(context, intent, null);
                    }
                    3 -> {//favoritos
                        val context = holder.itemView.context
                        val intent =Intent(context, MyFavoritesActivity::class.java)
                        startActivity(context, intent, null);
                    }
                    5 -> {//minhas inscrições
                        val context = holder.itemView.context
                        val intent =Intent(context, MySubscriptionsActivity::class.java)
                        startActivity(context, intent, null);
                    }
                    6 -> {//pacotes
                        val context = holder.itemView.context
                        val intent =Intent(context, MyPackagesActivity::class.java)
                        startActivity(context, intent, null);
                    }
                    7 -> {//histórico de marcações
                        val context = holder.itemView.context
                        val intent =Intent(context, MyAppointmentsActivity::class.java)
                        startActivity(context, intent, null);
                    }
                    8 -> {//despesas
                        val context = holder.itemView.context
                        val intent =Intent(context, MyExpensesActivity::class.java)
                        startActivity(context, intent, null);
                    }
                    profileOptionsList.size - 1 -> {//mudar palavra-passe
                        val context = holder.itemView.context
                        val intent =Intent(context, ChangePasswordActivity::class.java)
                        startActivity(context, intent, null);
                    }
                }

            }
        }

        override fun getItemCount(): Int {
            return profileOptionsList.size
        }
}