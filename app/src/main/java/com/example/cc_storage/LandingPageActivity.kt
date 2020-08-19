package com.example.cc_storage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction

class LandingPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        try {
            this.supportActionBar?.hide()
        } catch (e: NullPointerException) {}

        supportFragmentManager.beginTransaction()
            .replace(R.id.landingPageContainer, LandingPage1Fragment())
            .commit()
    }

    fun ubahHalamanFragment2(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.landingPageContainer, LandingPage2Fragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    fun ubahHalamanFragment3(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.landingPageContainer, LandingPage3Fragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
}