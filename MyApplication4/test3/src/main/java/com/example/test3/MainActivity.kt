package com.example.test3

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test3.databinding.ActivityMain6Binding
import com.example.test3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMain6Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMain6Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val numberButtons = listOf(
            binding.textView1,
            binding.textView2,
            binding.textView3,
            binding.textView4,
            binding.textView5,
            binding.textView6,
            binding.textView7,
            binding.textView8,
            binding.textView9,
            binding.textView10,
            binding.textView11,
            binding.textView12
        )

        for (button in numberButtons) {
            button.setOnClickListener {
                val number = button.text.toString()
                val currentText = binding.editTextText6.text.toString()
                binding.editTextText6.setText(currentText + number)
            }
        }

    }

}









