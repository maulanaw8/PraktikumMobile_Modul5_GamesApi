package com.example.gamesapi.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gamesapi.databinding.ListViewItemBinding
import com.example.gamesapi.network.Game

class GameListAdapter(val clickListener: GameListener) :
    ListAdapter<Game, GameListAdapter.GameViewHolder>(DiffCallback) {

    class GameViewHolder(
        var binding: ListViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: GameListener, game: Game){
            binding.game = game
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Game>(){

        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.thumbnail == newItem.thumbnail && oldItem.short_description == newItem.short_description
                    && oldItem.genre == newItem.genre && oldItem.platform == newItem.platform
                    && oldItem.developer == newItem.developer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GameViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int){
        val game = getItem(position)
        holder.bind(clickListener, game)
    }

}

class GameListener(val clickListener: (game: Game) -> Unit) {
    fun onClick(game: Game) = clickListener(game)
}