package com.example.espacocultural

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView


class MainActivity8 : AppCompatActivity() {

    private lateinit var backIcon: ImageView
    private lateinit var confirmEdit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main8)

        backIcon = findViewById(R.id.back_icon)
        confirmEdit = findViewById(R.id.confirmButton)

        /// Configuração do ícone de voltar
        backIcon.setOnClickListener {
            // Volta para a tela MainActivity
            val intent = Intent(this, MainActivity7::class.java)
            startActivity(intent)
        }

        /// config

        confirmEdit.setOnClickListener {
            Toast.makeText(this, "Edição concluída", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity7::class.java)
            startActivity(intent)
        }
    }
}