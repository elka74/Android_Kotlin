package com.example.android_kotlin.ui.vieewModel

import androidx.annotation.VisibleForTesting
import com.example.android_kotlin.data.model.Note
import com.example.android_kotlin.data.model.NoteResult.Error
import com.example.android_kotlin.data.model.NoteResult.Success
import com.example.android_kotlin.data.model.Repository
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(repository: Repository) :
    BaseViewModel<List<Note>?>() {

    private val noteChannel by lazy { runBlocking { repository.getNotes() } }

    init {
        launch {
            noteChannel.consumeEach { result ->
                when (result) {
                    is Success<*> -> setData((result.data as? List<Note>))
                    is Error -> setError(result.error)
                }
            }
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    public override fun onCleared() {
        noteChannel.cancel()
        super.onCleared()
    }
}





