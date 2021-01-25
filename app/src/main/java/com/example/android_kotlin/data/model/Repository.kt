package com.example.android_kotlin.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

object Repository {
    private val notesLiveData = MutableLiveData<List<Note>>()
    private val notes: MutableList<Note> = mutableListOf(
        Note(
            id = UUID.randomUUID().toString(),
            title = "Моя первая заметка",
            note = "Kotlin очень краткий, но при этом выразительный язык",
            color = Color.BLUE
        ),
        Note(
            id = UUID.randomUUID().toString(),
            title = "Моя вторая заметка",
            note = "Я люблю узнавать что - то новое, поэтому с интересом жду следующих занятий",
            color = Color.GREEN
        ),
        Note(
            id = UUID.randomUUID().toString(),
            title = "Моя третья заметка",
            note = "Kotlin - красивый язык",
            color = Color.VIOLET
        ),
        Note(
            id = UUID.randomUUID().toString(),
            title = "Моя четвертая  заметка",
            note = "Краткость - сестра таланта",
            color = Color.PINK
        ),
        Note(
            id = UUID.randomUUID().toString(),
            title = "Моя пятая заметка",
            note = "Ненвозможно научить тому, чего не умеешь сам. Поэтому я пошла учиться)))",
            color = Color.YELLOW
        )
    )

    init {
        notesLiveData.value = notes
    }

    fun getNotes(): LiveData<List<Note>> = notesLiveData

    fun saveNote(note: Note) {
        addOrReplace(note)
        notesLiveData.value = notes
    }

    private fun addOrReplace(note: Note) {

        for (i in 0 until notes.size) {
            if (notes[i] == note) {
                notes[i] = note
                return
            }
        }

        notes.add(note)
    }
}
