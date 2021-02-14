package com.example.android_kotlin.ui.vieewModel

import com.example.android_kotlin.data.model.NoAuthException
import com.example.android_kotlin.data.model.Repository
import kotlinx.coroutines.launch

class SplashViewModel(private val repository: Repository) :
    BaseViewModel<Boolean>() {

    fun requestUser() {
        launch {
            repository.getCurrentUser()?.let {
                setData(true)
            } ?: setError(NoAuthException())
        }
    }
}