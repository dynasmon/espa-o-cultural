package com.example.espacocultural

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView


class MainActivity11 : AppCompatActivity() {

    private lateinit var backIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main11)

        backIcon = findViewById(R.id.back_icon)


        /// Configuração do ícone de voltar
        backIcon.setOnClickListener {
            // Volta para a tela MainActivity
            val intent = Intent(this, MainActivity7::class.java)
            startActivity(intent)
        }

        /// config


    }
}