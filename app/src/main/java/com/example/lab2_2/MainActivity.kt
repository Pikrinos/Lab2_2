package com.example.lab2_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.lab2_2.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var i: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textView3.text = i.toString()
        game()
    }

    private fun game() {
        binding.usertext.text.clear()

        val words = getWords()
        val mixedWords = words.toCharArray().let {
            it.shuffle()
            it.concatToString()
        }
        binding.textView2.text = mixedWords

        binding.button.setOnClickListener {
            val userText = binding.usertext.text.toString() ?: ""
            if(userText == words) {
                Toast.makeText(this,"Well done!",Toast.LENGTH_SHORT).show()
                game()
                i++
                binding.textView3.text = i.toString()
            }else {
                Toast.makeText(this, "No,try again!", Toast.LENGTH_SHORT).show()
                binding.usertext.text.clear()
            }
        }
    }

    private fun getWords(): String {
        val words = resources.getStringArray(R.array.words)
        return words[Random.nextInt(words.size)]
    }
}