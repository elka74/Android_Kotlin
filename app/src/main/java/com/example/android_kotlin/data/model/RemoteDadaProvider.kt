package com.example.android_kotlin.data.model

import androidx.lifecycle.LiveData

interface RemoteDadaProvider {

    fun subscribeToAllNotes(): LiveData<NoteResult>

    fun getNoteById(id: String): LiveData<NoteResult>

    fun saveNote(note: Note): LiveData<NoteResult>

    fun getCurrentUser(): LiveData<User?>

}