package com.example.espacocultural

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity9 : AppCompatActivity() {

    private lateinit var backIcon: ImageView
    private lateinit var confirmEdit: Button
    private lateinit var obraName: EditText
    private lateinit var obraDescription: EditText
    private lateinit var obraAuthor: EditText
    private lateinit var obraUrl: EditText

    private val firestore = FirebaseFirestore.getInstance() // Instância do Firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main9)

        // Inicialização dos componentes do layout
        backIcon = findViewById(R.id.back_icon)
        confirmEdit = findViewById(R.id.confirmButton)
        obraName = findViewById(R.id.editObraName) // Nome da obra
        obraDescription = findViewById(R.id.editObraDescription) // Descrição
        obraAuthor = findViewById(R.id.editObraAuthor) // Nome do artista
        obraUrl = findViewById(R.id.editObraUrl) // URL da imagem

        // Configuração do botão de voltar
        backIcon.setOnClickListener {
            val intent = Intent(this, MainActivity7::class.java)
            startActivity(intent)
        }

        // Configuração do botão de confirmar adição
        confirmEdit.setOnClickListener {
            val name = obraName.text.toString().trim()
            val description = obraDescription.text.toString().trim()
            val author = obraAuthor.text.toString().trim()
            val url = obraUrl.text.toString().trim()

            if (name.isEmpty() || description.isEmpty() || author.isEmpty() || url.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            } else {
                // Adiciona um log para depuração dos valores capturados
                Log.d("FirestoreDebug", "Name: $name, Description: $description, Author: $author, URL: $url")

                // Chama a função para adicionar ao Firestore
                addObraToFirestore(name, description, author, url)
            }
        }

    }

    private fun addObraToFirestore(name: String, description: String, author: String, url: String) {
        val obra = hashMapOf(
            "name" to name,
            "description" to description,
            "author" to author,
            "imageUrl" to url
        )

        firestore.collection("obras")
            .add(obra)
            .addOnSuccessListener {
                Toast.makeText(this, "Obra adicionada com sucesso!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity7::class.java)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Erro ao adicionar obra: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
