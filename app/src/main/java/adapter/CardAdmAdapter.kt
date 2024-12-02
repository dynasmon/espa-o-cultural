package adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.espacocultural.CardObra
import com.example.espacocultural.R

class CardAdmAdapter(
    private val cardList: List<CardObra>,
    private val onEditClick: (CardObra) -> Unit,
    private val onDeleteClick: (CardObra) -> Unit
) : RecyclerView.Adapter<CardAdmAdapter.CardViewHolder>() {

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardImage: ImageView = itemView.findViewById(R.id.card_image)
        val cardTitle: TextView = itemView.findViewById(R.id.card_title)
        val cardSubtitle: TextView = itemView.findViewById(R.id.card_subtitle)
        val editButton: ImageButton = itemView.findViewById(R.id.card_edit_button)
        val deleteButton: ImageButton = itemView.findViewById(R.id.card_delete_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_adm, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val cardItem = cardList[position]

        holder.cardImage.setImageResource(cardItem.imageResId)
        holder.cardTitle.text = cardItem.title
        holder.cardSubtitle.text = cardItem.subtitle

        holder.editButton.setOnClickListener { onEditClick(cardItem) }
        holder.deleteButton.setOnClickListener { onDeleteClick(cardItem) }
    }

    override fun getItemCount(): Int = cardList.size
}
