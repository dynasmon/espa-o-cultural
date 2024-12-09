package com.example.espacocultural

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity9 : AppCompatActivity() {

    private lateinit var editObraAuthor: EditText
    private lateinit var editObraName: EditText
    private lateinit var editObraDescription: EditText
    private lateinit var editObraImageUrl: EditText
    private lateinit var confirmButton: Button
    private lateinit var backIcon: ImageView
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main9)

        // Inicializando o Firestore
        firestore = FirebaseFirestore.getInstance()

        // Associando os campos do layout
        editObraAuthor = findViewById(R.id.editObraAuthor)
        editObraName = findViewById(R.id.editObraName)
        editObraDescription = findViewById(R.id.editObraDescription)
        editObraImageUrl = findViewById(R.id.editObraImageUrl)
        confirmButton = findViewById(R.id.confirmButton)
        backIcon = findViewById(R.id.back_icon)

        // Botão para confirmar a adição
        confirmButton.setOnClickListener {
            val author = editObraAuthor.text.toString()
            val name = editObraName.text.toString()
            val description = editObraDescription.text.toString()
            val imageUrl = editObraImageUrl.text.toString()

            if (author.isNotBlank() && name.isNotBlank() && description.isNotBlank() && imageUrl.isNotBlank()) {
                val obra = mapOf(
                    "author" to author,
                    "name" to name,
                    "description" to description,
                    "imageUrl" to imageUrl
                )

                firestore.collection("obras")
                    .add(obra)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Obra adicionada com sucesso!", Toast.LENGTH_SHORT).show()
                        finish() // Voltar para a atividade anterior
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Erro ao adicionar obra: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
        }

        // Botão para voltar
        backIcon.setOnClickListener {
            startActivity(Intent(this, MainActivity7::class.java))
        }
    }
}
