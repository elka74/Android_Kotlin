package com.example.android_kotlin.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*


object Repository {

    private val remoteDadaProvider: RemoteDadaProvider = FireStoreProvider()

    fun getNotes() = remoteDadaProvider.subscribeToAllNotes()

    fun saveNote(note: Note) = remoteDadaProvider.saveNote(note)

    fun getNoteById(id: String) = remoteDadaProvider.getNoteById(id)

    fun getCurrentUser() = remoteDadaProvider.getCurrentUser()

}
