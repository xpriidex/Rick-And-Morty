package com.xpridex.rickandmorty.ui.characterlist.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.xpridex.rickandmorty.databinding.ViewItemCharacterBinding
import com.xpridex.rickandmorty.domain.model.DomainCharacterItem

class CharacterViewHolder(val binding: ViewItemCharacterBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(attrs: DomainCharacterItem, onClickListener: (Int) -> Unit) {
        binding.apply {
            name.text = attrs.name
            speciesAndStatus.text = "${attrs.species} - ${attrs.status} "
            root.setOnClickListener { onClickListener.invoke(attrs.id) }
            Picasso.get().load(attrs.image)
                .fit().centerCrop()
                .into(image)
        }
    }
}
