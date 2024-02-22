package com.example.fdj_android

import FootballRepository
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private lateinit var footballRepository: FootballRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ApiServiceFactory.initialize(this)

        // Utilisation de la factory pour créer le service
        val footballApiService = ApiServiceFactory.create()

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, LeagueFragment.newInstance())
            .commit()

    }
}