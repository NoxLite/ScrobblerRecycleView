package com.example.scrobblerhomepage

import com.example.scrobblerrecycleview.R

class Track {
    private val name: String
    private val artist: String
    private val album: String
    private val duration: Int

    private var coverResId: Int = listOf(
        R.drawable.cover_1,
        R.drawable.cover_2,
        R.drawable.cover_3,
        R.drawable.cover_4,
        R.drawable.cover_5).random()

    constructor(name: String, artist: String, album: String, duration: Int) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    fun getName(): String {
        return name;
    }

    fun getArtist(): String {
        return artist;
    }

    fun getAlbum(): String {
        return album;
    }

    fun getDuration(): Int {
        return duration;
    }

    fun getCoverResId(): Int {
        return coverResId;
    }

    fun setCoverResId(coverResId: Int) {
        this.coverResId = coverResId;
    }
}
