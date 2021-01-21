package com.example.android_kotlin.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_kotlin.R
import com.example.android_kotlin.data.model.Note
import com.example.android_kotlin.databinding.ItemNoteBinding

class MainAdapter : RecyclerView.Adapter<NoteViewHolder>() {
    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int): Unit {
        holder.bind(notes[position])
    }

    override fun getItemCount() = notes.size
}

class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ui:ItemNoteBinding = ItemNoteBinding.bind(itemView)

    fun bind(note: Note) {
        with(note) {
            ui.title.text = this.title
            ui.body.text = this.note
            itemView.setBackgroundColor(note.color)
        }
    }
}
