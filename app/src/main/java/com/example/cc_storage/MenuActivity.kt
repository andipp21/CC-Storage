package com.example.cc_storage

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        try {
            this.supportActionBar?.hide()
        } catch (e: NullPointerException) {}
        val sharedPreferences = getSharedPreferences(userData.SP_NAME, Context.MODE_PRIVATE)

        val nama = sharedPreferences.getString(userData.FIELD_USERNAME, "Belum Ada Data")

        tvVsPemain.setText("$nama VS Pemain")
        tvVsCom.setText("$nama VS Komputer")

        ivVsPemain.setOnClickListener {
            val intent = Intent(this, MainVSPemain::class.java)
            startActivity(intent)
        }

        ivVsCom.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}