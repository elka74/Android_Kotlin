package com.example.android_kotlin.data.model


class Repository(private val remoteDadaProvider: RemoteDadaProvider) {

    suspend fun getNotes() = remoteDadaProvider.subscribeToAllNotes()
    suspend fun saveNote(note: Note) = remoteDadaProvider.saveNote(note)
    suspend fun getNoteById(id: String) = remoteDadaProvider.getNoteById(id)
    suspend fun getCurrentUser() = remoteDadaProvider.getCurrentUser()
    suspend fun deleteNote(noteId: String) = remoteDadaProvider.deleteNote(noteId)
}
