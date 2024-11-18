package com.example.espacocultural

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity5 : AppCompatActivity() {
    private lateinit var fontSizeSeekBar: SeekBar
    private lateinit var sampleTextView: TextView
    private lateinit var backIcon: ImageView
    private lateinit var confIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        // Inicialize as views
        fontSizeSeekBar = findViewById(R.id.font_size_seekbar)
        sampleTextView = findViewById(R.id.font_ex)
        backIcon = findViewById(R.id.back_icon)
        confIcon = findViewById(R.id.settings_icon)

        // Configure o SeekBar
        fontSizeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val newSize = 12 + progress
                sampleTextView.textSize = newSize.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Configuração do ícone de voltar
        backIcon.setOnClickListener {
            // Volta para a tela MainActivity
            val intent = Intent(this, MainActivity4::class.java)
            startActivity(intent)
        }

        // Configuração do ícone de configurações
        confIcon.setOnClickListener {
            // Vai para a tela MainActivity5 (ou a configuração desejada)
            val intent = Intent(this, MainActivity5::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
