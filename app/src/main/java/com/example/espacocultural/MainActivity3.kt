package com.example.espacocultural

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity3 : AppCompatActivity() {

    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var editConfirmPassword: EditText
    private lateinit var backIcon: ImageView
    private val firestore = FirebaseFirestore.getInstance() // Instância do Firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        editConfirmPassword = findViewById(R.id.editConfirmPassword)
        backIcon = findViewById(R.id.backIcon_3)

        // Configuração do botão de cadastrar
        val confirmButton = findViewById<MaterialButton>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            val email = editEmail.text.toString().trim()
            val password = editPassword.text.toString().trim()
            val confirmPassword = editConfirmPassword.text.toString().trim()

            // Validação simples
            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "As senhas não correspondem.", Toast.LENGTH_SHORT).show()
            } else {
                // Salva o usuário no Firestore
                saveUserToFirestore(email, password)
            }
        }

        // Navegar para a tela de login
        val enterText = findViewById<TextView>(R.id.enterText)
        enterText.setOnClickListener {
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

    private fun saveUserToFirestore(email: String, password: String) {
        val user = hashMapOf(
            "email" to email,
            "senha" to password // Use hashes para senhas em produção!
        )

        firestore.collection("admin_users")
            .add(user)
            .addOnSuccessListener {
                Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                // Navegar para a próxima tela
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Erro ao cadastrar: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

}
