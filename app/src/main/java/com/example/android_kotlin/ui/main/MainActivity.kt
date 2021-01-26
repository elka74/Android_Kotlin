package com.example.android_kotlin.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.android_kotlin.R
import com.example.android_kotlin.data.model.Note
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity() : BaseActivity<List<Note>?, MainViewState>(){



    override val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java) }
    override val layoutRes: Int = R.layout.activity_main
    private lateinit var adapter: MainAdapter
    //override lateinit var ui: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // ui = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(toolbar)

        adapter = MainAdapter( object: OnItemOnClickListener {
            override fun onItemClick(note: Note) {
                openNoteScreen(note)
            }
        })
        mainRecycler.adapter = adapter

        fab.setOnClickListener { openNoteScreen(null) }
    }

    override fun renderData(data: List<Note>?) {
        if (data == null) return
        adapter.notes = data
    }

    private fun openNoteScreen(note: Note? = null) {
        startActivity(NoteActivity.getStartIntent(this, note?.id))

    }

}