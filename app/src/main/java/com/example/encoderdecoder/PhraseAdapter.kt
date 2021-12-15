package com.example.encoderdecoder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.encoderdecoder.databinding.PhraseRowBinding

class PhraseAdapter(private var phrases: ArrayList<Phrase>): RecyclerView.Adapter<PhraseAdapter.PhraseViewHolder>() {
    class PhraseViewHolder(val binding: PhraseRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhraseViewHolder {
        return PhraseViewHolder(
            PhraseRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhraseViewHolder, position: Int) {
        val phrase = phrases[position]
        holder.binding.apply {
            tvPhrase.text = phrase.originalPhrase
            tvPhraseEncoderDecoder.text = phrase.modifiedPhrase
        }
    }

    override fun getItemCount() = phrases.size

    fun updateList(phrases: ArrayList<Phrase>) {
        this.phrases = phrases
        notifyDataSetChanged()
    }

}