import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thebarbershop.Models.Appointment
import com.example.thebarbershop.databinding.AppointmentsListItemBinding

class AppointmentsAdapter(private val appointmentsList: List<Appointment>) :
    RecyclerView.Adapter<AppointmentsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: AppointmentsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val barberName: TextView = binding.barberName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AppointmentsListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appointment = appointmentsList[position]
        holder.barberName.text = appointment.barber
        // Bind other data here as needed
    }

    override fun getItemCount(): Int {
        return appointmentsList.size
    }

    // Update the adapter's data
    /*fun updateData(newAppointmentsList: List<Appointment>) {
        appointmentsList.clear()
        appointmentsList.addAll(newAppointmentsList)
        notifyDataSetChanged()
    }*/
}
