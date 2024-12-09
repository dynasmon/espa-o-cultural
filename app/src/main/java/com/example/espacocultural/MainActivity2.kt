package com.example.espacocultural

import com.google.firebase.firestore.FirebaseFirestore
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity2 : AppCompatActivity() {

    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var backIcon: ImageView
    private lateinit var eyeIcon: ImageView
    private lateinit var firestore: FirebaseFirestore
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Inicializando Firestore
        firestore = FirebaseFirestore.getInstance()

        // Associando elementos do layout
        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        backIcon = findViewById(R.id.backIcon)
        eyeIcon = findViewById(R.id.eye_icon)

        // Botão de login
        val loginButton = findViewById<MaterialButton>(R.id.loginButton)
        loginButton.setOnClickListener {
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Chama a função de login com Firestore
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }

        // Navegar para a tela de cadastro
        val textView2 = findViewById<TextView>(R.id.textView2)
        textView2.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }

        // Voltar para a MainActivity
        backIcon.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Alternar visibilidade da senha
        eyeIcon.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                editPassword.inputType = android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                eyeIcon.setImageResource(R.drawable.ic_eye)
            } else {
                editPassword.inputType =
                    android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
                eyeIcon.setImageResource(R.drawable.ic_close_eye)
            }
            editPassword.setSelection(editPassword.text.length)
        }
    }

    private fun loginUser(email: String, password: String) {
        // Validar os campos antes de tentar o login
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show()
            return
        }

        // Consultar Firestore para verificar credenciais do administrador
        firestore.collection("admin_users")
            .whereEqualTo("email", email)
            .whereEqualTo("senha", password) // Certifique-se de que "senha" está correto no Firestore
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity7::class.java)
                    startActivity(intent)
                    finish() // Finalizar tela de login para não voltar ao pressionar "Voltar"
                } else {
                    Toast.makeText(this, "Email ou senha incorretos.", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Erro ao verificar login: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
