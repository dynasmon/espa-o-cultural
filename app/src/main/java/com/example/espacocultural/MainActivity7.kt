package com.example.espacocultural

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.appcompat.app.AlertDialog

class MainActivity7 : AppCompatActivity() {

    private lateinit var backIcon: ImageView
    private lateinit var confIcon: ImageView
    private lateinit var editTela1Image: ImageView
    private lateinit var novaObra: ImageView
    private lateinit var deletObra: ImageView
    private lateinit var imageIco: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        backIcon = findViewById(R.id.back_icon)
        confIcon = findViewById(R.id.settings_icon)
        editTela1Image = findViewById(R.id.button_edit)
        novaObra = findViewById(R.id.addIconImageView)
        deletObra = findViewById(R.id.button_delete)
        imageIco = findViewById(R.id.obra1)

        /// Configuração do ícone de voltar
        backIcon.setOnClickListener {
            // Volta para a tela MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        /// config
        confIcon.setOnClickListener {
            // Vai para a tela config
            val intent = Intent(this, MainActivity11::class.java)
            startActivity(intent)
        }

        editTela1Image.setOnClickListener {
            val intent = Intent(this, MainActivity8::class.java)
            startActivity(intent)
        }

        novaObra.setOnClickListener {
            val intent = Intent(this, MainActivity9::class.java)
            startActivity(intent)
        }

        imageIco.setOnClickListener {
            val intent = Intent(this, MainActivity13::class.java)
            startActivity(intent)
        }

        deletObra.setOnClickListener {
            showImageDialog()
        }
    }

    private fun showImageDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_image, null)
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setView(dialogView)

        val dialog = dialogBuilder.create()
        dialog.setCancelable(true)

        // Obtenha a ImageView do layout do dialog
        val imageView = dialogView.findViewById<ImageView>(R.id.dialog_image)
        imageView.setOnClickListener {
            // Quando a imagem for clicada, inicie a nova atividade
            val intent = Intent(this, MainActivity12::class.java) // Substitua NovaAtividade pela sua atividade de destino
            startActivity(intent)
            dialog.dismiss() // Fecha o dialog após iniciar a nova atividade
        }

        dialog.show()
    }
}
