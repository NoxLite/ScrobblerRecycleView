package com.example.scrobblerrecycleview

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scrobblerhomepage.AddSongDialog
import com.example.scrobblerhomepage.MainViewModel
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton


class MainActivity : AppCompatActivity() {

    lateinit var topLabel: TextView
    lateinit var viewModel: MainViewModel
    lateinit var addSongButton: ExtendedFloatingActionButton

    lateinit var historyAdapter: ScrobbleAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addSongButton = findViewById(R.id.add_track_button)
        topLabel = findViewById(R.id.top_label)

        val provider = ViewModelProvider(this)
        viewModel = provider[MainViewModel::class.java]

        historyAdapter = ScrobbleAdapter()

        val list = findViewById<RecyclerView>(R.id.history_list)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = historyAdapter

        addSongButton.setOnClickListener {
            val dialog = AddSongDialog()
            dialog.show(supportFragmentManager, "AddSongDialog")
        }

        observeLiveData()
    }

    fun observeLiveData() {
        viewModel.tracks.observe(this) {tracks ->
            run {
                val scrobbles: MutableList<Scrobble> = mutableListOf()
                for ((id, elem) in tracks.withIndex()) {
                    scrobbles += Scrobble(id, elem.getName(),
                        elem.getArtist(),
                        elem.getCoverResId()
                    )
                }
                historyAdapter.submitList(scrobbles)
            }
        }
    }
}