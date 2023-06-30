package com.example.marvelapplication.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapplication.databinding.ItemCharacterBinding
import com.example.marvelapplication.model.characters.MarvelCharacter
import com.example.marvelapplication.utils.Delegado
import com.example.marvelapplication.utils.getThumbnail
import com.example.marvelapplication.utils.loadImage
import javax.inject.Inject

class CharactersAdapter @Inject constructor() :
    PagingDataAdapter<MarvelCharacter, CharactersAdapter.ViewHolder>(diffCallback = differCallback) {

    private lateinit var binding: ItemCharacterBinding
    private lateinit var context: Context
    lateinit var delegate: Delegado

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MarvelCharacter) {
            binding.root.setOnClickListener {
                delegate.goToDetails(item)
            }
            binding.characterName.text = item.name
            binding.characterDescription.text = item.description
            binding.chImage.loadImage(item.thumbnail?.getThumbnail())
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)!!
        holder.setIsRecyclable(false)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(
            parent.context,
        )
        context = parent.context
        binding = ItemCharacterBinding.inflate(inflater, parent, false)
        return ViewHolder()
    }

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<MarvelCharacter>() {
            override fun areItemsTheSame(
                oldItem: MarvelCharacter,
                newItem: MarvelCharacter,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MarvelCharacter,
                newItem: MarvelCharacter,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
