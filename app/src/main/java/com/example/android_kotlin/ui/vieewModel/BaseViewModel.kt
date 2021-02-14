package com.example.android_kotlin.ui.vieewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_kotlin.ui.state.BaseViewState

open class BaseViewModel<T, VS: BaseViewState<T>>: ViewModel() {

    open val viewStateLiveData = MutableLiveData<VS>()

    open fun getViewState(): LiveData<VS> = viewStateLiveData

}