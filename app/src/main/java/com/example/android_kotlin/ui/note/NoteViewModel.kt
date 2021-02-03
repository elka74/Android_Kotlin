package com.example.android_kotlin.ui.note

import androidx.lifecycle.Observer
import com.example.android_kotlin.data.model.Note
import com.example.android_kotlin.data.model.NoteResult
import com.example.android_kotlin.data.model.NoteResult.Success
import com.example.android_kotlin.data.model.NoteResult.Error
import com.example.android_kotlin.data.model.Repository
import com.example.android_kotlin.ui.base.BaseViewModel


class NoteViewModel(val repository: Repository = Repository) :
    BaseViewModel<Note?, NoteViewState>() {

    private var pendingNote: Note? = null

    fun saveChanges(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        if (pendingNote != null) {
            repository.saveNote(pendingNote!!)
        }
    }

    fun loadNote(noteId: String) {
        repository.getNoteById(noteId).observeForever(object : Observer<NoteResult> {
            override fun onChanged(t: NoteResult?) {
                if (t == null) return

                when (t) {
                    is Success<*> ->
                        viewStateLiveData.value = NoteViewState(note = t.data as? Note)
                    is Error ->
                        viewStateLiveData.value = NoteViewState(error = t.error)
                }
            }
        })
    }


}



