package com.example.android_kotlin.ui.main

import com.example.android_kotlin.data.model.Note

class NoteViewState(note: Note? = null, error: Throwable? = null) : BaseViewState<Note?>(note, error)
