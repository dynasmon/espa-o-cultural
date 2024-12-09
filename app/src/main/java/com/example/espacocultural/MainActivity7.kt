package com.example.espacocultural

import adapter.ObraAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity7 : AppCompatActivity() {

    private lateinit var backIcon: ImageView
    private lateinit var confIcon: ImageView
    private lateinit var addIcon: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var firestore: FirebaseFirestore
    private lateinit var adapter: ObraAdapter
    private val obrasList = mutableListOf<Obra>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        // Inicializar Firestore
        firestore = FirebaseFirestore.getInstance()

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.cardRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Configurar Adapter
        adapter = ObraAdapter(
            obrasList,
            onEditClick = { obra -> showEditDialog(obra) },
            onDeleteClick = { obra -> showDeleteDialog(obra) }
        )
        recyclerView.adapter = adapter

        // Configurar Botões
        backIcon = findViewById(R.id.back_icon)
        confIcon = findViewById(R.id.settings_icon)
        addIcon = findViewById(R.id.addIconImageView2)

        backIcon.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        confIcon.setOnClickListener {
            startActivity(Intent(this, MainActivity11::class.java))
        }

        addIcon.setOnClickListener {
            showAddDialog()
        }

        // Carregar obras do Firestore
        loadObras()
    }

    private fun loadObras() {
        firestore.collection("obras")
            .get()
            .addOnSuccessListener { result ->
                obrasList.clear()
                for (document in result) {
                    val obra = Obra(
                        id = document.id,
                        name = document.getString("name") ?: "",
                        description = document.getString("description") ?: "",
                        imageUrl = document.getString("imageUrl") ?: ""
                    )
                    obrasList.add(obra)
                }
                adapter.updateData(obrasList)
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Erro ao buscar obras", exception)
            }
    }

    private fun showAddDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_obra, null)
        val nameInput = dialogView.findViewById<EditText>(R.id.editTextName)
        val descriptionInput = dialogView.findViewById<EditText>(R.id.editTextDescription)
        val imageUrlInput = dialogView.findViewById<EditText>(R.id.editTextImageUrl)

        AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton("Salvar") { _, _ ->
                val name = nameInput.text.toString()
                val description = descriptionInput.text.toString()
                val imageUrl = imageUrlInput.text.toString()

                if (name.isNotBlank() && description.isNotBlank() && imageUrl.isNotBlank()) {
                    val obra = mapOf(
                        "name" to name,
                        "description" to description,
                        "imageUrl" to imageUrl
                    )
                    firestore.collection("obras")
                        .add(obra)
                        .addOnSuccessListener {
                            loadObras()
                            Toast.makeText(this, "Obra adicionada!", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Erro ao adicionar obra.", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancelar", null)
            .create()
            .show()
    }


    private fun showEditDialog(obra: Obra) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_edit_obra, null)
        val nameInput = dialogView.findViewById<EditText>(R.id.editTextName)
        val descriptionInput = dialogView.findViewById<EditText>(R.id.editTextDescription)

        nameInput.setText(obra.name)
        descriptionInput.setText(obra.description)

        AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton("Salvar") { _, _ ->
                val name = nameInput.text.toString()
                val description = descriptionInput.text.toString()

                if (name.isNotBlank() && description.isNotBlank()) {
                    val updatedObra = mapOf(
                        "name" to name,
                        "description" to description
                    )
                    firestore.collection("obras").document(obra.id)
                        .update(updatedObra)
                        .addOnSuccessListener {
                            loadObras()
                            Toast.makeText(this, "Obra atualizada!", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Erro ao atualizar obra.", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancelar", null)
            .create()
            .show()
    }

    private fun showDeleteDialog(obra: Obra) {
        AlertDialog.Builder(this)
            .setMessage("Deseja excluir esta obra?")
            .setPositiveButton("Excluir") { _, _ ->
                firestore.collection("obras").document(obra.id)
                    .delete()
                    .addOnSuccessListener {
                        loadObras()
                        Toast.makeText(this, "Obra excluída!", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Erro ao excluir obra.", Toast.LENGTH_SHORT).show()
                    }
            }
            .setNegativeButton("Cancelar", null)
            .create()
            .show()
    }
}
