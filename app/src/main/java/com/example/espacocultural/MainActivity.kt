package com.example.espacocultural

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var welcomeText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        welcomeText = findViewById(R.id.welcomeText)

        // Botão Testar Conexão com o Banco de Dados
        val buttonTestDatabase = findViewById<MaterialButton>(R.id.buttonTestDatabase)
        buttonTestDatabase.setOnClickListener {
            Log.d("MainActivity", "Botão Testar Conexão clicado")
            val intent = Intent(this, DatabaseTestConnection::class.java)
            startActivity(intent)
        }

        // Botão Entrar como Administrador
        val buttonAdmin = findViewById<MaterialButton>(R.id.button1)
        buttonAdmin.setOnClickListener {
            Log.d("MainActivity", "Botão Administrador clicado")
            // welcomeText.text = "Administrador clicado!"
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        // Botão Entrar como Visitante
        val buttonVisitor = findViewById<MaterialButton>(R.id.button2)
        buttonVisitor.setOnClickListener {
            Log.d("MainActivity", "Botão Visitante clicado")
            // welcomeText.text = "Visitante clicado!"
            val intent = Intent(this, MainActivity4::class.java)
            startActivity(intent)
        }

        // Botão Brasileiro (🇧🇷)
        val buttonBR = findViewById<MaterialButton>(R.id.buttonBR)
        buttonBR.setOnClickListener {
            Log.d("MainActivity", "Botão BR clicado")
            // Mudando os textos dos botões para português
            buttonAdmin.text = getString(R.string.entrar_como_administrador)
            buttonVisitor.text = getString(R.string.entrar_como_visitante)
            welcomeText.text = getString(R.string.bem_vindo)
        }


        // Botão Americano (🇺🇸)
        val buttonUS = findViewById<MaterialButton>(R.id.buttonUS)
        buttonUS.setOnClickListener {
            Log.d("MainActivity", "Botão US clicado")
            // Mudando os textos dos botões
            buttonAdmin.text = "Enter as Admin"
            buttonVisitor.text = "Enter as Visitor"
            welcomeText.text = "Welcome"
        }

    }
}
