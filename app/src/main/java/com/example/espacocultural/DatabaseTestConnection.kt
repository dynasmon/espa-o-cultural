package com.example.espacocultural

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class DatabaseTestConnection : AppCompatActivity() {

    private val firestoreManager = FirestoreManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database_test_connection)

        setupButtonListeners()
    }

    private fun setupButtonListeners() {
        val etName = findViewById<EditText>(R.id.etName)
        val etAge = findViewById<EditText>(R.id.etAge)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etSearchName = findViewById<EditText>(R.id.etSearchName)

        // Botão para adicionar dados
        findViewById<Button>(R.id.btnAddData)?.setOnClickListener {
            val name = etName.text.toString()
            val age = etAge.text.toString()
            val email = etEmail.text.toString()

            if (name.isNotEmpty() && age.isNotEmpty() && email.isNotEmpty()) {
                addPerson(name, age.toInt(), email)
            } else {
                Log.e("FirestoreTest", "Por favor, preencha todos os campos.")
            }
        }

        // Botão para buscar dados por nome
        findViewById<Button>(R.id.btnSearchData)?.setOnClickListener {
            val searchName = etSearchName.text.toString()
            if (searchName.isNotEmpty()) {
                searchPersonByName(searchName)
            } else {
                Log.e("FirestoreTest", "Por favor, insira um nome para buscar.")
            }
        }
    }

    private fun addPerson(name: String, age: Int, email: String) {
        val data = mapOf(
            "name" to name,
            "age" to age,
            "email" to email
        )

        firestoreManager.addOrUpdateDocument("colecao_teste", null, data, object : FirestoreManager.FirestoreCallback {
            override fun onSuccess(message: String) {
                Log.d("FirestoreTest", "Pessoa adicionada com sucesso: $message")
            }

            override fun onFailure(errorMessage: String) {
                Log.e("FirestoreTest", "Erro ao adicionar pessoa: $errorMessage")
            }
        })
    }

    private fun searchPersonByName(name: String) {
        firestoreManager.getAllDocuments("colecao_teste", object : FirestoreManager.FirestoreListCallback {
            override fun onSuccess(data: List<Map<String, Any>>) {
                val result = data.find { it["name"] == name }
                if (result != null) {
                    Log.d("FirestoreTest", "Pessoa encontrada: $result")
                } else {
                    Log.d("FirestoreTest", "Pessoa não encontrada.")
                }
            }

            override fun onFailure(errorMessage: String) {
                Log.e("FirestoreTest", "Erro ao buscar pessoa: $errorMessage")
            }
        })
    }
}
