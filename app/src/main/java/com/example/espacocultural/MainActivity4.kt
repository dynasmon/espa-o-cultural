package com.example.espacocultural

import adapter.CardAdapter
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

        // Inicializar Views
        backIcon = findViewById(R.id.back_icon)
        confIcon = findViewById(R.id.settings_icon)
        tela1Image = findViewById(R.id.tela1)
        recyclerView = findViewById(R.id.recyclerView)

        // Criar a lista de obras de arte
        val obrasList = listOf(
            CardVisitante(R.drawable.tarsila, "Tarsila do Amaral", "Paisagem, 1931"),
            CardVisitante(R.drawable.cavalcanti, "Di Cavalcanti", "Bahia, 1935"),
            CardVisitante(R.drawable.basquiat, "Basquiat", "Trumpet, 1984"),
            CardVisitante(R.drawable.botero, "Fernando Botero", "Le soralle, 1968"),
            CardVisitante(R.drawable.caravaggio, "Caravaggio", "The incredulity of saint thomas, 1601"),
            CardVisitante(R.drawable.michelangelo, "Michelangelo", "Creation of Adam, 1512")
        )

        // Configurar o LayoutManager da RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Configurar o adapter
        if (obrasList.isNotEmpty()) {
            val adapter = CardAdapter(obrasList)
            recyclerView.adapter = adapter
        }

        // Configuração dos ícones de navegação
        backIcon.setOnClickListener {
            navigateToActivity(MainActivity::class.java)
        }

        confIcon.setOnClickListener {
            navigateToActivity(MainActivity5::class.java)
        }

        tela1Image.setOnClickListener {
            navigateToActivity(MainActivity6::class.java)
        }
    }

    // Função auxiliar para navegação entre atividades
    private fun navigateToActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}
