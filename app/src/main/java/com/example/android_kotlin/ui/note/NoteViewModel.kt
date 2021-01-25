package com.example.android_kotlin.ui.note

import androidx.lifecycle.ViewModel
import com.example.android_kotlin.data.model.Note
import com.example.android_kotlin.data.model.Repository

data class NoteViewModel(private val repository: Repository = Repository) : ViewModel() {

    private var pendingNote: Note? = null

    fun saveChanges(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        if (pendingNote != null) {
            repository.saveNote(pendingNote!!)
        }
    }
}
