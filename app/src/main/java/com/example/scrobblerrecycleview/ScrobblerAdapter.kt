package com.example.scrobblerrecycleview
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ScrobbleAdapter :
    ListAdapter<Scrobble, ScrobbleAdapter.Holder>(DiffCallback) {

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val trackName =
            view.findViewById<TextView>(R.id.track_name)

        private val artistName =
            view.findViewById<TextView>(R.id.artist_name)

        private val albumImage =
            view.findViewById<ImageView>(R.id.album_image)

        fun bind(item: Scrobble) {
            trackName.text = item.name
            artistName.text = item.artist
            albumImage.setImageResource(item.album)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_scrobble, parent, false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Scrobble>() {

            override fun areItemsTheSame(
                oldItem: Scrobble,
                newItem: Scrobble
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Scrobble,
                newItem: Scrobble
            ): Boolean = oldItem == newItem
        }
    }
}