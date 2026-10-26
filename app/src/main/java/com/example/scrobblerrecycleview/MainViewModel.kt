package com.example.scrobblerhomepage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val tracks = MutableLiveData<List<Track>>(emptyList())

    fun addTrack(track: Track) {
        val trackList = tracks.value?.toMutableList() ?: mutableListOf()
        tracks.value = trackList + track
    }

    fun getTrackList(): List<Track> {
        return tracks.value?: emptyList()
    }

    fun getArtistList(): List<String> {
        return tracks.value?.map { it.getArtist() } ?: emptyList()
    }

    fun getAlbumList(): List<String> {
        return tracks.value?.map { it.getAlbum() } ?: emptyList()
    }

    fun getCountMinutes(): Int {
        var result: Int = 0;
        for (elem in tracks.value ?: emptyList()) {
            result += elem.getDuration()
        }
        return result
    }

}