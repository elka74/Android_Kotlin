package com.example.android_kotlin.ui.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_kotlin.R
import com.example.android_kotlin.data.model.Color
import com.example.android_kotlin.data.model.Note
import com.example.android_kotlin.databinding.ItemNoteBinding
import com.example.android_kotlin.ui.getColorInt

interface OnItemOnClickListener {
    fun onItemClick(note: Note)

}

class MainAdapter(private val onItemClickListener: OnItemOnClickListener) :
    RecyclerView.Adapter<MainAdapter.NoteViewHolder>() {
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


    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ui: ItemNoteBinding = ItemNoteBinding.bind(itemView)

        fun bind(note: Note) {
            with(note) {
                ui.title.text = title
                ui.body.text = this.note

                ui.container.setCardBackgroundColor(note.color.getColorInt(itemView.context))
                itemView.setOnClickListener { onItemClickListener.onItemClick(note) }
            }
        }
    }
}
