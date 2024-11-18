package com.example.espacocultural

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class DatabaseTestConnection : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database_test_connection)

        // Inicializa o Realtime Database com o URL
        val database = FirebaseDatabase.getInstance("https://espacocultural-1b2bd-default-rtdb.firebaseio.com/")
        val myRef = database.getReference("/")
        myRef.child("mensagem").setValue("Olá, Firebase!")


        myRef.child("mensagem").get().addOnSuccessListener { dataSnapshot ->
            Log.d("FirebaseTest", "Dados lidos: ${dataSnapshot.value}")
        }.addOnFailureListener { e ->
            Log.e("FirebaseTest", "Erro ao ler dados: ${e.message}")
        }


    }
}
