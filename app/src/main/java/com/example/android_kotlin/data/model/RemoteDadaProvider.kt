package com.example.android_kotlin.data.model

import kotlinx.coroutines.channels.ReceiveChannel

interface RemoteDadaProvider {

    suspend fun subscribeToAllNotes(): ReceiveChannel<NoteResult>
    suspend fun getNoteById(id: String): Note
    suspend fun saveNote(note: Note): Note
    suspend fun getCurrentUser(): User?
    suspend fun deleteNote(noteId: String): Note?

}