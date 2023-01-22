package dev.hirogakatageri.github.demo.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import dev.hirogakatageri.github.demo.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding?.let { b -> setContentView(b.root) }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}