package com.example.android_kotlin.ui.vieewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.android_kotlin.data.model.Note
import com.example.android_kotlin.data.model.NoteResult
import com.example.android_kotlin.data.model.NoteResult.Success
import com.example.android_kotlin.data.model.NoteResult.Error
import com.example.android_kotlin.data.model.Repository
import com.example.android_kotlin.ui.state.NoteViewState


class NoteViewModel(val repository: Repository) :
    BaseViewModel<NoteViewState.Data, NoteViewState>() {

    private val currentNote: Note?
        get() = viewStateLiveData.value?.data?.note


    fun saveChanges(note: Note) {
        viewStateLiveData.value = NoteViewState(NoteViewState.Data(note = note))
    }

    override fun onCleared() {
        currentNote?.let { repository.saveNote(it) }

    }

    fun loadNote(noteId: String) {
        repository.getNoteById(noteId).observeForever { t ->
            t?.let {
                viewStateLiveData.value = when (t) {
                    is Success<*> ->
                        NoteViewState(NoteViewState.Data(note = t.data as? Note))
                    is Error ->
                        NoteViewState(error = t.error)
                }
            }
        }
    }


    fun deleteNote() {
        currentNote?.let {
            repository.deleteNote(it.id).observeForever { result ->
                result?.let { noteResult ->
                    viewStateLiveData.value = when (noteResult) {
                        is Success<*> ->
                            NoteViewState(NoteViewState.Data(isDeleted = true))
                        is Error ->
                            NoteViewState(error = noteResult.error)
                    }
                }
            }
        }
    }
}




