package com.example.android_kotlin.data.model

import com.example.android_kotlin.data.model.Note

object Repository{
    val notes: List<Note> = listOf(
        Note("Моя первая заметка",
            "Kotlin очень краткий, но при этом выразительный язык",
            0xfff06292.toInt()),
        Note("Моя вторая заметка",
            "Я люблю узнавать что - то новое, поэтому с интересом жду следующих занятий",
            0xff9575cd.toInt()),
        Note("Моя третья заметка",
            "Kotlin - красивый язык",
            0xff64b5f6.toInt()),
    )
    }
