package com.example.android_kotlin.ui.state

import com.example.android_kotlin.data.model.Note


class MainViewState(notes: List<Note>? = null, error: Throwable? = null)
    : BaseViewState<List<Note>?>(notes, error)
