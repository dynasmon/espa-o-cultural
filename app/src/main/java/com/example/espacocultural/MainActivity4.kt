package com.example.espacocultural

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView


class MainActivity4 : AppCompatActivity() {

    private lateinit var backIcon: ImageView
    private lateinit var confIcon: ImageView
    private lateinit var tela1Image:CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        backIcon = findViewById(R.id.back_icon)
        confIcon = findViewById(R.id.settings_icon)
        tela1Image = findViewById(R.id.tela1)

        /// Configuração do ícone de voltar
        backIcon.setOnClickListener {
            // Volta para a tela MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        /// config
        confIcon.setOnClickListener {
            // Vai para a tela config
            val intent = Intent(this, MainActivity5::class.java)
            startActivity(intent)
        }
        tela1Image.setOnClickListener {
            val intent = Intent(this, MainActivity6::class.java)
            startActivity(intent)
        }
    }
}