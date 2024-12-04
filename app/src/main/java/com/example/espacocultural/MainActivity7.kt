package com.example.espacocultural

import adapter.CardAdmAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity7 : AppCompatActivity() {

    private lateinit var backIcon: ImageView
    private lateinit var confIcon: ImageView
    private lateinit var editTela1Image: ImageView
    private lateinit var novaObra: ImageView
    private lateinit var deletObra: ImageView
    private lateinit var imageIco: ImageView

    private lateinit var recyclerView: RecyclerView
    private lateinit var cardList: MutableList<CardObra>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        backIcon = findViewById(R.id.back_icon)
        confIcon = findViewById(R.id.settings_icon)

        // Inicializando o RecyclerView
        recyclerView = findViewById(R.id.cardRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Dados para o RecyclerView
        cardList = mutableListOf(
            CardObra(R.drawable.tarsila, "Tarsila do Amaral", "O pescador, 1925"),
            CardObra(R.drawable.cavalcanti, "Di Cavalcanti", "Bahia, 1935"),
            CardObra(R.drawable.botero, "Fernando Botero", "Le soralle, 1968"),
            CardObra(R.drawable.basquiat, "Basquiat", "Trumpet, 1984")
        )

        // Configurando o RecyclerView com o Adapter
        recyclerView.adapter = CardAdmAdapter(
            cardList,
            onEditClick = { card ->
                val intent = Intent(this, MainActivity8::class.java)
                // Passando as informações para edição
                intent.putExtra("imageResId", card.imageResId)
                intent.putExtra("title", card.title)
                intent.putExtra("subtitle", card.subtitle)
                startActivity(intent)
            },
            onDeleteClick = { card ->
                val dialog = AlertDialog.Builder(this)
                dialog.setMessage("Você tem certeza que deseja deletar este item?")
                    .setPositiveButton("Sim") { _, _ ->
                        cardList.remove(card)
                        recyclerView.adapter?.notifyDataSetChanged()
                    }
                    .setNegativeButton("Não", null)
                dialog.show()
            }
        )

        // Configuração do ícone de voltar
        backIcon.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Configuração do ícone de configurações
        confIcon.setOnClickListener {
            val intent = Intent(this, MainActivity11::class.java)
            startActivity(intent)
        }

        editTela1Image.setOnClickListener {
            val intent = Intent(this, MainActivity8::class.java)
            startActivity(intent)
        }

        novaObra.setOnClickListener {
            val intent = Intent(this, MainActivity9::class.java)
            startActivity(intent)
        }

        imageIco.setOnClickListener {
            val intent = Intent(this, MainActivity13::class.java)
            startActivity(intent)
        }

        deletObra.setOnClickListener {
            showImageDialog()
        }
    }

    private fun showImageDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_image, null)
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setView(dialogView)

        val dialog = dialogBuilder.create()
        dialog.setCancelable(true)

        // Obtenha a ImageView do layout do dialog
        val imageView = dialogView.findViewById<ImageView>(R.id.dialog_image)
        imageView.setOnClickListener {
            val intent = Intent(this, MainActivity12::class.java)
            startActivity(intent)
            dialog.dismiss()
        }

        dialog.show()
    }
}
