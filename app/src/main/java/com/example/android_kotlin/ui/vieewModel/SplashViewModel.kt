package com.example.android_kotlin.ui.vieewModel

import com.example.android_kotlin.data.model.NoAuthException
import com.example.android_kotlin.data.model.Repository
import com.example.android_kotlin.ui.state.SplashViewState

class SplashViewModel(private val repository: Repository) :
    BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser() {
        repository.getCurrentUser().observeForever { user ->
            viewStateLiveData.value = user?.let {
                SplashViewState(isAuth = true)
            } ?: SplashViewState(error = NoAuthException())
        }
    }
}
