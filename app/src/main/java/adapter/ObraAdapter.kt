package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.espacocultural.Obra
import com.example.espacocultural.R

class ObraAdapter(
    private val obras: MutableList<Obra>,
    private val onEditClick: (Obra) -> Unit,
    private val onDeleteClick: (Obra) -> Unit
) : RecyclerView.Adapter<ObraAdapter.ObraViewHolder>() {

    fun updateData(newObras: List<Obra>) {
        obras.clear()
        obras.addAll(newObras)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObraViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.obra_item, parent, false)
        return ObraViewHolder(view)
    }

    override fun onBindViewHolder(holder: ObraViewHolder, position: Int) {
        val obra = obras[position]
        holder.name.text = obra.name
        holder.description.text = obra.description
        holder.editButton.setOnClickListener { onEditClick(obra) }
        holder.deleteButton.setOnClickListener { onDeleteClick(obra) }
    }

    override fun getItemCount() = obras.size

    inner class ObraViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.obra_name)
        val description: TextView = view.findViewById(R.id.obra_description)
        val editButton: ImageView = view.findViewById(R.id.edit_button)
        val deleteButton: ImageView = view.findViewById(R.id.delete_button)
    }
}
