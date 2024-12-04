package com.example.espacocultural

import CardAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity4 : AppCompatActivity() {

    private lateinit var backIcon: ImageView
    private lateinit var confIcon: ImageView
    private lateinit var tela1Image: CardView
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        backIcon = findViewById(R.id.back_icon)
        confIcon = findViewById(R.id.settings_icon)
        tela1Image = findViewById(R.id.tela1)
        recyclerView = findViewById(R.id.recyclerView)

        // Crie a lista de obras de arte
        val obrasList = listOf(
            CardVisitante(R.drawable.tarsila, "Tarsila do Amaral", "Paisagem, 1931"),
            CardVisitante(R.drawable.cavalcanti, "Di Cavalcanti", "Bahia, 1935"),
            CardVisitante(R.drawable.basquiat, "Basquiat", "Trumpet, 1984"),
            CardVisitante(R.drawable.botero, "Fernando Botero", "Le soralle, 1968"),
            CardVisitante(R.drawable.caravaggio, "Caravaggio", "The incredulity of saint thomas, 1931"),
            CardVisitante(R.drawable.michelangelo, "Michelangelo", "Le soralle, 1968")
        )

        // Configure o LayoutManager (linear vertical)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Configure o adapter com a lista de obras
        val adapter = CardAdapter(obrasList)
        recyclerView.adapter = adapter

        

        // Configuração dos ícones
        backIcon.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        confIcon.setOnClickListener {
            val intent = Intent(this, MainActivity5::class.java)
            startActivity(intent)
        }

        tela1Image.setOnClickListener {
            val intent = Intent(this, MainActivity6::class.java)
            startActivity(intent)
        }
    }
}
