package com.rdelacosta.itunessearch.ui.musicList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rdelacosta.itunessearch.data.entities.Music
import com.rdelacosta.itunessearch.databinding.ItemMusicBinding
import com.squareup.picasso.Picasso

class MusicAdapter(private val listener: MusicItemListener) : ListAdapter<Music, MusicAdapter.MusicViewHolder>(DiffCallback()) {

    interface MusicItemListener {
        fun onClickedItem(id : Long)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val binding = ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MusicViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class MusicViewHolder(private val binding: ItemMusicBinding, private val listener: MusicItemListener) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private lateinit var music: Music

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind (musicObj: Music) {
            music = musicObj
            binding.apply {
                textArtist.text = musicObj.artistName
                textTrack.text = musicObj.trackName
                textPrice.text = musicObj.trackPrice
                if (!musicObj.trackViewUrl.isNullOrEmpty()) {
                    Picasso.get()
                            .load(musicObj.trackViewUrl)
                            //.placeholder(R.drawable.)
                            .into(imgThumbnail)
                }
            }
        }

        override fun onClick(v: View?) {
            listener.onClickedItem(music.id)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Music>() {
        override fun areItemsTheSame(oldItem: Music, newItem: Music) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Music, newItem: Music) =
            oldItem == newItem
    }

}