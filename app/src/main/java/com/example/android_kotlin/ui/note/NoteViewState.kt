package com.example.android_kotlin.ui.note

import com.example.android_kotlin.data.model.Note
import com.example.android_kotlin.ui.base.BaseViewState

class NoteViewState(note: Note? = null, error: Throwable? = null) : BaseViewState<Note?>(note, error)
