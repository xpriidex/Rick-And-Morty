package com.xpridex.rickandmorty.ui.characterlist.adapter

import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xpridex.rickandmorty.databinding.ViewItemCharacterBinding
import com.xpridex.rickandmorty.domain.model.DomainCharacterItem
import com.xpridex.rickandmorty.ui.characterlist.adapter.viewholder.CharacterViewHolder

class ListCharacterAdapter(
    val items: List<DomainCharacterItem>,
    val onClickListener: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ViewItemCharacterBinding.inflate(from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val summary = items[position]
        (holder as CharacterViewHolder).bind(summary, onClickListener)
    }
}
