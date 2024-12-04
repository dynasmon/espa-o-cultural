package adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.espacocultural.CardVisitante
import com.example.espacocultural.R

class CardAdapter(private val items: List<CardVisitante>) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    // ViewHolder que irá segurar as referências dos componentes do layout
    class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val subtitleTextView: TextView = view.findViewById(R.id.subtitleTextView)
    }

    // Criação do ViewHolder a partir do layout card_visitante
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_visitante, parent, false)
        return CardViewHolder(view)
    }

    // Bind dos dados ao ViewHolder
    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = items[position]
        holder.imageView.setImageResource(item.imageResId)
        holder.titleTextView.text = item.titulo
        holder.subtitleTextView.text = item.subtitulo
    }

    // Retorna o número de itens na lista
    override fun getItemCount(): Int = items.size
}
