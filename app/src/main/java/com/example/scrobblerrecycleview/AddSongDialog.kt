package com.example.scrobblerhomepage

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.scrobblerrecycleview.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class AddSongDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        val viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        val content = layoutInflater.inflate(R.layout.add_song_dialog, null)

        val trackInput = content.findViewById<EditText>(R.id.track_input)
        val artistInput = content.findViewById<EditText>(R.id.artist_input)
        val albumInput = content.findViewById<EditText>(R.id.album_input)
        val durationMinutesInput = content.findViewById<EditText>(R.id.minutes_input)
        val durationSecondsInput = content.findViewById<EditText>(R.id.seconds_input)

        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle("Добавить скроббл")
            .setView(content)
            .setNegativeButton("Отмена", null)
            .setPositiveButton("Добавить", null)
            .create()

        dialog.setOnShowListener {
            val addButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            addButton.setOnClickListener {
                val track = trackInput.text.toString().trim()
                val artist = artistInput.text.toString().trim()
                val album = albumInput.text.toString().trim()

                val minutes = durationMinutesInput.text.toString().toIntOrNull()
                val seconds = durationSecondsInput.text.toString().toIntOrNull()

                trackInput.error = null
                artistInput.error = null
                durationMinutesInput.error = null
                durationSecondsInput.error = null

                var isValid = true

                if (track.isBlank()) {
                    trackInput.error = "Укажите название трека"
                    isValid = false
                }

                if (artist.isBlank()) {
                    artistInput.error = "Укажите исполнителя"
                    isValid = false
                }

                if (minutes == null || minutes < 0) {
                    durationMinutesInput.error = "Введите минуты"
                    isValid = false
                }

                if (seconds == null || seconds < 0 || seconds > 59) {
                    durationSecondsInput.error = "Введите секунды от 0 до 59"
                    isValid = false
                }

                if (isValid) {
                    val duration = minutes!!.toLong() * 60 + seconds!!
                    var track = Track(track, artist, album, duration.toInt())

                    for (elem in viewModel.getTrackList()) {
                        if (elem.getAlbum() == track.getAlbum() && elem.getArtist() == track.getArtist()) {
                            track.setCoverResId(elem.getCoverResId())
                            break
                        }
                    }
                    viewModel.addTrack(
                        track
                    )
                    dialog.dismiss()
                }
            }
        }

        return dialog
    }
}