package com.example.encoderdecoder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.encoderdecoder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val phrases = ArrayList<Phrase>()
    lateinit var phraseRecyclerView: RecyclerView
    lateinit var phraseAdapter: PhraseAdapter
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRV()

        binding.btnEncode.setOnClickListener {
            val originalPhrase = binding.etPhrase.text.toString()
            binding.etPhrase.text.clear()

            if (originalPhrase.isNotEmpty()) {
                val modifiedPhrase = caesar(originalPhrase,13)
                phrases.add(Phrase(originalPhrase, modifiedPhrase ))
                phraseAdapter.updateList(phrases)
                phraseRecyclerView.scrollToPosition(phrases.size - 1)
            } else {
                Toast.makeText(this, "Blank phrase is invalid!", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnDecode.setOnClickListener {
            val originalPhrase = binding.etPhrase.text.toString()
            binding.etPhrase.text.clear()

            if (originalPhrase.isNotEmpty()) {
                val modifiedPhrase = caesar(originalPhrase,-13)
                phrases.add(Phrase(originalPhrase, modifiedPhrase ))
                phraseAdapter.updateList(phrases)
                phraseRecyclerView.scrollToPosition(phrases.size - 1)
            } else {
                Toast.makeText(this, "Blank phrase is invalid!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupRV() {
        phraseRecyclerView = binding.rvPhrase
        phraseAdapter = PhraseAdapter(phrases)
        phraseRecyclerView.adapter = phraseAdapter
        phraseRecyclerView.layoutManager = LinearLayoutManager(this)
    }



    private fun caesar(phrase: String, place: Int) : String {
        var encodePhrase = ""

        for (letter in phrase) {
            // each letter of the phrase should be moved 13 places to the right.
            var c = ' '
            when (letter) {
                in 'a'..'z' -> {
                    c = letter + place
                    if (c > 'z')
                        c -= 26
                    else if (c < 'a')
                        c += 26
                }
                in 'A'..'Z' -> {
                    c = letter + place
                    if (c > 'Z')
                        c -= 26
                    else if (c < 'A')
                        c += 26
                }
                // Spaces, numbers, and special characters should be returned as they are.
                else -> c = letter
            }
            encodePhrase += c
        }
        return encodePhrase
    }
}