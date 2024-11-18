package com.example.espacocultural

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity3 : AppCompatActivity() {

    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var editConfirmPassword: EditText
    private lateinit var backIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        editConfirmPassword = findViewById(R.id.editConfirmPassword)
        backIcon = findViewById(R.id.backIcon_3)

        // Configuração do botão de cadastrar
        val loginButton = findViewById<MaterialButton>(R.id.loginButton)
        loginButton.setOnClickListener {
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            val confirmPassword = editConfirmPassword.text.toString()

            // Validação simples
            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "As senhas não correspondem.", Toast.LENGTH_SHORT).show()
            } else {
                // Aqui você pode adicionar a lógica para salvar o usuário em um banco de dados, se necessário
                Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
                finish() // Fecha a tela de cadastro
            }
        }

        // Navegar para a tela de login
        val textView2 = findViewById<TextView>(R.id.textView2)
        textView2.setOnClickListener {
            // Volta para a tela de login (MainActivity2)
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        // Configuração do ícone de voltar
        backIcon.setOnClickListener {
            // Volta para a tela MainActivity2
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}
