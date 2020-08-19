package com.example.cc_storage

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val sharedPreferences = getSharedPreferences(userData.SP_NAME, Context.MODE_PRIVATE)

        try {
            this.supportActionBar?.hide()
        } catch (e: NullPointerException) {}

        val handler = Handler()

        handler.postDelayed({

            val contains = sharedPreferences.contains(userData.FIELD_USERNAME)

            if (!contains) {
                val editor = sharedPreferences.edit()

                editor.putString(userData.FIELD_USERNAME, "sabrina")
                editor.putString(userData.FIELD_PASSWORD, "binar123")
                editor.putString(userData.FIELD_EMAIL, "sabrina@binar.co.id")

                editor.apply()
            }

            startActivity(Intent(this, LandingPageActivity::class.java))

            finish()
        }, 1000)
    }
}