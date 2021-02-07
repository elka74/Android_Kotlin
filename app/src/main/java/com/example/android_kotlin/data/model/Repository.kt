package com.example.android_kotlin.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*


class Repository(private val remoteDadaProvider: RemoteDadaProvider) {

    fun getNotes() = remoteDadaProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteDadaProvider.saveNote(note)
    fun getNoteById(id: String) = remoteDadaProvider.getNoteById(id)
    fun getCurrentUser() = remoteDadaProvider.getCurrentUser()
    fun deleteNote(noteId: String) = remoteDadaProvider.deleteNote(noteId)
}
