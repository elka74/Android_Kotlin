package com.example.android_kotlin.ui.vieewModel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import com.example.android_kotlin.data.model.Note
import com.example.android_kotlin.data.model.NoteResult
import com.example.android_kotlin.data.model.NoteResult.Error
import com.example.android_kotlin.data.model.NoteResult.Success
import com.example.android_kotlin.data.model.Repository
import com.example.android_kotlin.ui.state.MainViewState

class MainViewModel(repository: Repository) :
    BaseViewModel<List<Note>?, MainViewState>() {

    private val notesObserver = object : Observer<NoteResult> {
        override fun onChanged(t: NoteResult?) {
            if (t == null) return

            when (t) {
                is Success<*> -> {
                    viewStateLiveData.value = MainViewState(notes = t.data as? List<Note>)
                }
                is Error -> {

                    viewStateLiveData.value = MainViewState(error = t.error)
                }
            }
        }
    }

    private val repositoryNotes = repository.getNotes()

    init {
        viewStateLiveData.value = MainViewState()
        repositoryNotes.observeForever(notesObserver)
    }
    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    public override fun onCleared() {
        repositoryNotes.removeObserver(notesObserver)
    }
}





